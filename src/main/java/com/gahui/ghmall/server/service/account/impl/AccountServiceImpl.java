package com.gahui.ghmall.server.service.account.impl;

import com.gahui.ghmall.server.cache.SequenceCacheService;
import com.gahui.ghmall.server.constant.CacheEnum;
import com.gahui.ghmall.server.constant.ExceptionEnum;
import com.gahui.ghmall.server.dao.AccountDao;
import com.gahui.ghmall.server.dao.UserDao;
import com.gahui.ghmall.server.dto.AccountDto;
import com.gahui.ghmall.server.dto.UserDto;
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
    AccountDao accountDao;

    @Resource
    TokenService tokenService;

    @Resource
    SequenceCacheService sequenceCacheService;

    @Resource
    UserDao userDao;

    @Override
    public String login(String accountName, String password) {
        AccountDto accountDto = accountDao.getAccountByName(accountName);
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

    @Override
    public int validateAccountName(String accountName) {
        return accountDao.countAccountByName(accountName);
    }

    /**
     * 事务控制
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int register(AccountDto accountDto) {
        this.paramValidate(accountDto);
        Integer accountId = sequenceCacheService.getSeqIdByEnum(CacheEnum.ACCOUNT);
        accountDto.setAccountId(accountId);
        // 密码加密
        try {
            accountDto.setAccountPassword(AesHelper.encrypt(aesKey, accountDto.getAccountPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("密码加密错误！password ===> {}", accountDto.getAccountPassword());
            throw new GhmallException(ExceptionEnum.BIZ.getCode(), "密码加密错误！");
        }
        UserDto userDto = new UserDto();
        this.paramFill(userDto, accountDto);
        // 简化处理，账户与用户，一一对应，即userId == accountId
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
    private void paramValidate(AccountDto accountDto) {
        if (accountDto == null) {
            throw new GhmallException(ExceptionEnum.BIZ.getCode(), "注册信息为空！");
        }
        if (accountDto.getAccountName() == null || "".equals(accountDto.getAccountName().trim())) {
            throw new GhmallException(ExceptionEnum.BIZ.getCode(), "账户名为空！");
        }
        if (accountDao.countAccountByName(accountDto.getAccountName()) > 0) {
            throw new GhmallException(ExceptionEnum.BIZ.getCode(), "账户已经存在！");
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
    private void paramFill(UserDto userDto, AccountDto accountDto) {
        if (accountDto == null) {
            return;
        }
        UserDto temp = accountDto.getUser();
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
    private int insertAccount(AccountDto accountDto) {
        GhAccount account = new GhAccount();
        BeanUtils.copyProperties(accountDto, account);
        accountDao.insertSelective(account);
        GhUser user = new GhUser();
        BeanUtils.copyProperties(accountDto.getUser(), user);
        userDao.insertSelective(user);
        return 1;
    }

}
