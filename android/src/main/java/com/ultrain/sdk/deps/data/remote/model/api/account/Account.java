package com.ultrain.sdk.deps.data.remote.model.api.account;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * @author redli
 * @date 2019/3/29
 */
public class Account {

    /**
     * account_name : smallred1111
     * head_block_num : 17028129
     * head_block_time : 2019-03-29T02:47:18.500
     * privileged : false
     * last_code_update : 1970-01-01T00:00:00.000
     * created : 2019-03-13T03:01:13.000
     * core_liquid_balance : 100.0060 TOK
     * ram_quota : 15839
     * net_weight : 1000
     * cpu_weight : 1000
     * net_limit : {"used":473,"available":"174555583469","max":"174555583942"}
     * cpu_limit : {"used":2278,"available":"33293833160","max":"33293835438"}
     * ram_usage : 3124
     * permissions : [{"perm_name":"active","parent":"owner","required_auth":{"threshold":1,"keys":[{"key":"UTR6WHbXoUckcHNmwiVH2PiNV4mL4hJLyxegXGShM7dFrJ9DVQFZ7","weight":1}],"accounts":[],"waits":[]}},{"perm_name":"owner","parent":"","required_auth":{"threshold":1,"keys":[{"key":"UTR6WHbXoUckcHNmwiVH2PiNV4mL4hJLyxegXGShM7dFrJ9DVQFZ7","weight":1}],"accounts":[],"waits":[]}}]
     * total_resources : {"owner":"smallred1111","net_weight":"0.1000 TOK","cpu_weight":"0.1000 TOK","ram_bytes":15839}
     * self_delegated_bandwidth : null
     * refund_request : null
     * voter_info : null
     */
    @Expose
    private String account_name;
    @Expose
    private int head_block_num;
    @Expose
    private String head_block_time;
    @Expose
    private boolean privileged;
    @Expose
    private String last_code_update;
    @Expose
    private String created;
    @Expose
    private String core_liquid_balance;
    @Expose
    private int ram_quota;
    @Expose
    private int net_weight;
    @Expose
    private int cpu_weight;
    @Expose
    private NetLimit net_limit;
    @Expose
    private CpuLimit cpu_limit;
    @Expose
    private int ram_usage;
    @Expose
    private TotalResources total_resources;
    @Expose
    private Object self_delegated_bandwidth;
    @Expose
    private Object refund_request;
    @Expose
    private Object voter_info;
    @Expose
    private List<Permissions> permissions;

    public NetLimit getNet_limit() {
        return net_limit;
    }

    public void setNet_limit(NetLimit net_limit) {
        this.net_limit = net_limit;
    }

    public CpuLimit getCpu_limit() {
        return cpu_limit;
    }

    public void setCpu_limit(CpuLimit cpu_limit) {
        this.cpu_limit = cpu_limit;
    }

    public TotalResources getTotal_resources() {
        return total_resources;
    }

    public void setTotal_resources(TotalResources total_resources) {
        this.total_resources = total_resources;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public int getHead_block_num() {
        return head_block_num;
    }

    public void setHead_block_num(int head_block_num) {
        this.head_block_num = head_block_num;
    }

    public String getHead_block_time() {
        return head_block_time;
    }

    public void setHead_block_time(String head_block_time) {
        this.head_block_time = head_block_time;
    }

    public boolean isPrivileged() {
        return privileged;
    }

    public void setPrivileged(boolean privileged) {
        this.privileged = privileged;
    }

    public String getLast_code_update() {
        return last_code_update;
    }

    public void setLast_code_update(String last_code_update) {
        this.last_code_update = last_code_update;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCore_liquid_balance() {
        return core_liquid_balance;
    }

    public void setCore_liquid_balance(String core_liquid_balance) {
        this.core_liquid_balance = core_liquid_balance;
    }

    public int getRam_quota() {
        return ram_quota;
    }

    public void setRam_quota(int ram_quota) {
        this.ram_quota = ram_quota;
    }

    public int getNet_weight() {
        return net_weight;
    }

    public void setNet_weight(int net_weight) {
        this.net_weight = net_weight;
    }

    public int getCpu_weight() {
        return cpu_weight;
    }

    public void setCpu_weight(int cpu_weight) {
        this.cpu_weight = cpu_weight;
    }

    public int getRam_usage() {
        return ram_usage;
    }

    public void setRam_usage(int ram_usage) {
        this.ram_usage = ram_usage;
    }

    public Object getSelf_delegated_bandwidth() {
        return self_delegated_bandwidth;
    }

    public void setSelf_delegated_bandwidth(Object self_delegated_bandwidth) {
        this.self_delegated_bandwidth = self_delegated_bandwidth;
    }

    public Object getRefund_request() {
        return refund_request;
    }

    public void setRefund_request(Object refund_request) {
        this.refund_request = refund_request;
    }

    public Object getVoter_info() {
        return voter_info;
    }

    public void setVoter_info(Object voter_info) {
        this.voter_info = voter_info;
    }

}
