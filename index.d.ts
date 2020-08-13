export declare enum UChainEndpoints {
    MainNet = "https://ultrainio.ultrain.info",
    PioneerNet = "https://pioneer.ultrain.info",
    UnitopiaNet = "https://unitopia.ultrain.info",
    NewRetailNet = "https://newretail.ultrain.info",
    AustraliaNet = "https://australia.ultrain.info",
    SafariNet = "https://safari.ultrain.info",
    TestNet = "https://test-main.ultrain.info",
    TestPioneerNet = "https://test-pioneer.ultrain.info",
    TestPowerNet = "https://test-power.ultrain.info"
}
export interface RsaKeyPair {
    mnemonics?: string;
    privateKey: string;
    publicKey: string;
}
export declare class Crypto {
    static sha256: (message: string) => Promise<string>;
}
export declare class ECC {
    /**
     * 生成一对公私钥及助记词
     */
    static generatePrivateKeyWithMnemonic: () => Promise<RsaKeyPair>;
    /**
     * 通过助记词生成公私钥
     * @param mnemonics 助记词
     */
    static generatePrivateKeyByMnemonic: (mnemonics: string) => Promise<RsaKeyPair>;
    /**
     * 通过私钥计算公钥
     * @param privateKey 私钥
     */
    static getPublicKey: (privateKey: string) => Promise<string>;
    /**
     * 对交易进行签名, 但是不发送交易
     * @param url 链地址
     * @param txInfo 交易信息
     */
    static signTx: (url: UChainEndpoints, txInfo: TxInfo) => Promise<string>;
}
declare type KeysStruct = {
    key: string;
    weight: number;
};
export declare type AccountInfo = {
    account_name: string;
    head_block_num: number;
    head_block_time: string;
    empowerchains: string[];
    privileged: boolean;
    updateable: boolean;
    last_code_update: string;
    created: string;
    ram_quota: number;
    net_weight: number;
    cpu_weight: number;
    net_limit: {
        used: number;
        available: number;
        max: number;
    };
    cpu_limit: {
        used: number;
        available: number;
        max: number;
    };
    ram_usage: number;
    permissions: {
        perm_name: 'owner' | 'active';
        parent: string;
        required_auth: {
            threshold: number;
            keys: KeysStruct[];
            accounts: string[];
            waits: number[];
        };
    }[];
    chain_resource: {
        chain_name: string;
        lease_num: number;
        locked_num: number;
        start_time: string;
        end_time: string;
    }[];
};
export declare type AccountKeys = {
    [key: string]: KeysStruct[];
    active: KeysStruct[];
    owner: KeysStruct[];
};
export declare type CurrencyBalanceInfo = string[];
export declare type ChainInfo = {
    server_version: string;
    chain_id: string;
    head_block_num: number;
    last_irreversible_block_num: number;
    last_irreversible_block_id: string;
    head_block_id: string;
    head_block_time: string;
    head_block_proposer: string;
    virtual_block_cpu_limit: number;
    virtual_block_net_limit: number;
    block_cpu_limit: number;
    block_net_limit: number;
    block_interval_ms: number;
};
export declare type TransferFeeInfo = {
    fee: string;
};
export declare type PushTxResult = string;
export declare type TxInfo = {
    contract: string;
    action: string;
    account: string;
    privateKey: string;
    permissionLevel: 'active' | 'owner';
    params: any;
};
export declare type TransferInfo = {
    from: string;
    to: string;
    quantity: string;
    memo: string;
    actor: string;
    privateKey: string;
};
export declare class UChain {
    /**
     * 获取账号的信息
     * @param url 链地址
     * @param account 账号
     */
    static getAccount: (url: UChainEndpoints, account: string) => Promise<AccountInfo>;
    /**
     * 获取账号的公钥信息
     * @param url 链地址
     * @param account 账号
     */
    static getPublicKeysOfAccount: (url: UChainEndpoints, account: string) => Promise<AccountKeys>;
    /**
     * 获取链信息
     * @param url 链地址
     */
    static getChainInfo: (url: UChainEndpoints) => Promise<ChainInfo>;
    /**
     * 查询账号的余额
     * @param url 链地址
     * @param params 合约和账号信息
     */
    static getCurrencyBalance: (url: UChainEndpoints, params: {
        account: string;
        contract: string;
        symbol: string;
    }) => Promise<CurrencyBalanceInfo>;
    /**
     * 查询交易手续费
     * @param url 链地址
     * @param blockHeight 链上某个块的块高
     */
    static getTransferFee: (url: UChainEndpoints, blockHeight: number) => Promise<TransferFeeInfo>;
    /**
     * 发送交易
     * @param url 链地址
     * @param txInfo 交易信息
     */
    static pushTransaction: (url: UChainEndpoints, txInfo: TxInfo) => Promise<string>;
    /**
     * 转账操作
     * @param url 链地址
     * @param transferInfo 转账的信息
     */
    static transfer: (url: UChainEndpoints, transferInfo: TransferInfo) => Promise<string>;
}
export {};
