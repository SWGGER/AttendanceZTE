//package com.zzxmh.userservice.config.shiro;
//
//
//import com.zzxhm.yyjhservice.domain.auth.TPermission;
//import com.zzxhm.yyjhservice.domain.auth.TRole;
//import com.zzxhm.yyjhservice.domain.auth.TRolePermission;
//import com.zzxhm.yyjhservice.domain.auth.TUserRole;
//import com.zzxhm.yyjhservice.domain.user.TUser;
//import com.zzxhm.yyjhservice.service.auth.TPermissionService;
//import com.zzxhm.yyjhservice.service.auth.TRolePermissionService;
//import com.zzxhm.yyjhservice.service.auth.TRoleService;
//import com.zzxhm.yyjhservice.service.auth.TUserRoleService;
//import com.zzxhm.yyjhservice.service.user.TUserService;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
////实现AuthorizingRealm接口用户用户认证
//public class MyShiroRealm extends AuthorizingRealm {
//
//    @Autowired
//    private TUserService tUserService;
//    @Autowired
//    private TUserRoleService tUserRoleService;
//    @Autowired
//    private TRoleService tRoleService;
//    @Autowired
//    private TRolePermissionService tRolePermissionService;
//    @Autowired
//    private TPermissionService tPermissionService;
//
//    //角色权限和对应权限添加 principals 权限
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {//进行权限验证
//        //直接获取登录用户名   存在于session里面
//        String name= principals.getPrimaryPrincipal().toString();
//        //查询用户
//        TUser user= tUserService.getuserInfoByLoginId(name);
//
//        //添加角色和权限   告诉shrio这个用户有哪些角色和权限
//        List<TUserRole> userRoleList=tUserRoleService.getUserAllRole(user.getId());
//        TRole trole=new TRole();
//        List<TRolePermission> trp=new ArrayList<>();
//
//        //查询角色
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        for (TUserRole role:userRoleList) {
//            trole=tRoleService.getRoleName(role.getRoleId());
//            //添加角色
//            simpleAuthorizationInfo.addRole(trole.getRolename());
//
//            trp=tRolePermissionService.getPermissionid(role.getRoleId());
//            for(TRolePermission tRolePermission:trp) {
//                List<TPermission> permissions = tPermissionService.getPermissionByPermissionId(tRolePermission.getPermissionId());
//
//                for (TPermission permission : permissions) {
//                    //添加权限
//                    simpleAuthorizationInfo.addStringPermission(permission.getPermission());
//                }
//            }
//        }
//        return simpleAuthorizationInfo;
//    }
//
//    //用户认证  token 认证与用户登录有关 密码、账户   loginshiro
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {//进行身份验证的(登录验证)
//        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
//        if (token.getPrincipal() == null) {
//            return null;
//        }
//        //获取用户信息
//        String name= token.getPrincipal().toString();
//        //查询用户
//        TUser user = tUserService.getuserInfoByLoginId(name);
//        if (user == null) {
//            //这里返回后会报出对应异常
//            return null;
//        } else {
//            //这里验证token和simpleAuthenticationInfo的信息
//            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name,user.getPassword(),getName());
//            return simpleAuthenticationInfo;
//        }
//    }
//
//}