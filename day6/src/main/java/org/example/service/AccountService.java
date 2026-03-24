package org.example.service;
import org.example.entity.Account;
import org.example.entity.TransactionRecord;
import org.example.mapper.AccountMapper;
import org.example.mapper.TransactionRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;


    @Autowired
    private TransactionRecordMapper transactionRecordMapper;
    @Transactional
    public void initTables(){

    }
    /**
     * 1. 创建新用户（增加）
     */
    @Transactional
    public void createAccount(Account account) {
        account.setCoupon(0);  // 初始点券为0
        int result = accountMapper.insert(account);
        if (result > 0) {
            System.out.println("用户创建成功，ID: " + account.getId());
        }
    }

    /**
     * 2. 显示所有用户
     */
    public List<Account> listAllAccounts() {
        return accountMapper.selectAll();
    }

    /**
     * 3. 登录验证
     */
    public Account login(String account, String password) {
        Account user = accountMapper.login(account, password);
        if (user != null) {
            System.out.println("登录成功，当前点券: " + user.getCoupon());
        } else {
            System.out.println("登录失败，帐号或密码错误");
        }
        return user;
    }

    /**
     * 4. 充值500点券
     * timeout = 5 表示事务超时5秒
     * 执行时间超过5秒，自动回滚
     */
    @Transactional(timeout = 5, rollbackFor = Exception.class)
    public void recharge(String account, int amount) throws InterruptedException {
        System.out.println("开始充值: " + amount + " 点券");
        accountMapper.updateCoupon(account, amount);
        TransactionRecord record = new TransactionRecord();
        record.setAccount(account);
        record.setUpdateTime(new Date());
        record.setCategory("充值");
        record.setNum(amount);

        System.out.println("模拟网络延迟6秒");
        Thread.sleep(6000);  // 延迟6秒 > 5秒超时时间

        System.out.println("充值成功，点券已增加");
    }

    /**
     * 5. 消费
     */
    @Transactional(rollbackFor = Exception.class)
    public void consume(String account, int amount) {
        System.out.println("开始消费: " + amount + " 点券");

        // 1. 先检查点券是否足够
        Account user = accountMapper.selectByAccount(account);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if (user.getCoupon() < amount) {
            throw new RuntimeException("点券不足，当前点券: " + user.getCoupon());
        }

        // 2. 更新用户点券（减少）
        accountMapper.updateCoupon(account, -amount);

        // 3. 记录消费记录
        TransactionRecord record = new TransactionRecord();
        record.setAccount(account);
        record.setUpdateTime(new Date());
        record.setCategory("消费");
        record.setNum(amount);
        transactionRecordMapper.insert(record);

        System.out.println("消费成功，已扣除 " + amount + " 点券");
    }

    /**
     * 6. 查询用户交易记录
     */
    public List<TransactionRecord> getTransactionRecords(String account) {
        return transactionRecordMapper.selectByAccount(account);
    }
}