package com.fui.cloud.model;

public class JwtUserRolesKey extends BaseModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_user_roles.oauth_user_id
     *
     * @mbggenerated
     */
    private Long oauthUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_user_roles.roles_id
     *
     * @mbggenerated
     */
    private Long rolesId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_user_roles.oauth_user_id
     *
     * @return the value of oauth_user_roles.oauth_user_id
     *
     * @mbggenerated
     */
    public Long getOauthUserId() {
        return oauthUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_user_roles.oauth_user_id
     *
     * @param oauthUserId the value for oauth_user_roles.oauth_user_id
     *
     * @mbggenerated
     */
    public void setOauthUserId(Long oauthUserId) {
        this.oauthUserId = oauthUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_user_roles.roles_id
     *
     * @return the value of oauth_user_roles.roles_id
     *
     * @mbggenerated
     */
    public Long getRolesId() {
        return rolesId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_user_roles.roles_id
     *
     * @param rolesId the value for oauth_user_roles.roles_id
     *
     * @mbggenerated
     */
    public void setRolesId(Long rolesId) {
        this.rolesId = rolesId;
    }
}