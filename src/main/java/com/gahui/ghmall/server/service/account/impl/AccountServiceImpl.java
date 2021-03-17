package com.gahui.ghmall.server.service.account.impl;

import com.gahui.ghmall.server.cache.AccountCacheService;
import com.gahui.ghmall.server.constant.ExceptionEnum;
import com.gahui.ghmall.server.dao.GhAccountDao;
import com.gahui.ghmall.server.dao.GhUserDao;
import com.gahui.ghmall.server.dto.GhAccountDto;
import com.gahui.ghmall.server.dto.GhUserDto;
import com.gahui.ghmall.server.entity.GhAccount;
import com.gahui.ghmall.server.entity.GhUser;
import com.gahui.ghmall.server.exception.GhmallException;
import com.gahui.ghmall.server.service.account.AccountService;
import com.gahui.ghmall.server.service.token.TokenService;
import com.gahui.ghmall.server.util.AesHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @description: 账户业务逻辑具体实现类
 * @author: Gahui
 * @since: 2021/3/16
 **/
@Slf4j
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Value("${aes.key}")
    private String aesKey;

    @Resource
    GhAccountDao ghAccountDao;

    @Resource
    TokenService tokenService;

    @Resource
    AccountCacheService accountCacheService;

    @Resource
    GhUserDao ghUserDao;

    @Override
    public String login(String accountName, String password) {
        GhAccountDto accountDto = ghAccountDao.getAccountByName(accountName);
        if (accountDto == null) {
            throw new GhmallException(ExceptionEnum.BIZ.getCode(), "用户不存在！");
        }
        try {
            if (AesHelper.encrypt(aesKey, password.trim()).equals(accountDto.getAccountPassword())) {
                return tokenService.encode(accountDto.getAccountId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("用户密码加密错误，accountName ===> {}，password ===> {}", accountName, password);
            throw new GhmallException(ExceptionEnum.BIZ.getCode(), "密码加密错误！");
        }
        return null;
    }

    /**
     * 事务控制
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int register(GhAccountDto accountDto) {
        this.paramValidate(accountDto);
        Integer accountId = accountCacheService.getAccountId();
        accountDto.setAccountId(accountId);
        // 密码加密
        try {
            accountDto.setAccountPassword(AesHelper.encrypt(aesKey, accountDto.getAccountPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("密码加密错误！password ===> {}", accountDto.getAccountPassword());
            throw new GhmallException(ExceptionEnum.BIZ.getCode(), "密码加密错误！");
        }
        GhUserDto userDto = new GhUserDto();
        this.paramFill(userDto, accountDto);
        // 简化处理，userId == accountId
        userDto.setUserId(accountId);
        userDto.setAccountId(accountId);
        accountDto.setUser(userDto);
        return this.insertAccount(accountDto);
    }

    /**
     * 参数校验
     *
     * @param accountDto dto
     */
    private void paramValidate(GhAccountDto accountDto) {
        if (accountDto == null) {
            throw new GhmallException(ExceptionEnum.BIZ.getCode(), "注册信息为空！");
        }
        if (accountDto.getAccountName() == null || "".equals(accountDto.getAccountName().trim())) {
            throw new GhmallException(ExceptionEnum.BIZ.getCode(), "账户名为空！");
        }
        if (accountDto.getAccountPassword() == null || "".equals(accountDto.getAccountPassword())) {
            throw new GhmallException(ExceptionEnum.BIZ.getCode(), "密码为空！");
        }
    }

    /**
     * 用户数据填充
     *
     * @param userDto    填充的用户dto
     * @param accountDto 账户dto
     */
    private void paramFill(GhUserDto userDto, GhAccountDto accountDto) {
        if (accountDto == null) {
            return;
        }
        GhUserDto temp = accountDto.getUser();
        if (temp != null) {
            BeanUtils.copyProperties(temp, userDto);
        }
    }

    /**
     * 数据库中插入用户信息
     *
     * @param accountDto 账户dto
     * @return 1:成功
     */
    private int insertAccount(GhAccountDto accountDto) {
        GhAccount account = new GhAccount();
        BeanUtils.copyProperties(accountDto, account);
        ghAccountDao.insertSelective(account);
        GhUser user = new GhUser();
        BeanUtils.copyProperties(accountDto.getUser(), user);
        ghUserDao.insertSelective(user);
        return 1;
    }

}
