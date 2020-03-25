package cn.itcast.travel.dao.impl;


import cn.itcast.travel.domain.User;

public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 用户保存
     * @param user
     */
    public void save(User user);

	public User findByCode(String code);

	public void updateStatus(User user);

	

	public User findByUsernameAndPassword(String username, String password);

    
}
