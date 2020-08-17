import {NativeModules} from 'react-native';

const UltrainApiNative = NativeModules.UltrainSdkModule;

export enum UChainEndpoints {
  MainNet = 'https://ultrainio.ultrain.info', // 主链
  PioneerNet = 'https://pioneer.ultrain.info', // 先锋链
  UnitopiaNet = 'https://unitopia.ultrain.info', // 电魂链
  NewRetailNet = 'https://newretail.ultrain.info', // 新零售链
  AustraliaNet = 'https://australia.ultrain.info', // 澳洲链
  SafariNet = 'https://safari.ultrain.info', // Safari链

  TestNet = 'https://test-main.ultrain.info', // 测试网主链
  TestPioneerNet = 'https://test-pioneer.ultrain.info', // 测试网先锋链
  TestPowerNet = 'https://test-power.ultrain.info', // 测试网动力链
}

export interface RsaKeyPair {
  mnemonics?: string;
  privateKey: string;
  publicKey: string;
}

// type RsaKeyPair = any;
export class Crypto {
  static sha256 = async (message: string): Promise<string> => {
    let digest = await UltrainApiNative.sha256(message);
    return digest;
  };
}

export class ECC {
  /**
   * 生成一对公私钥及助记词
   */
  static generatePrivateKeyWithMnemonic = async (): Promise<RsaKeyPair> => {
    let rsa = await UltrainApiNative.generatePrivate();
    return rsa;
  };
  /**
   * 通过助记词生成公私钥
   * @param mnemonics 助记词
   */
  static generatePrivateKeyByMnemonic = async (
    mnemonics: string,
  ): Promise<RsaKeyPair> => {
    let rsa = await UltrainApiNative.seedPrivate(mnemonics);
    return rsa;
  };
  /**
   * 通过私钥计算公钥
   * @param privateKey 私钥
   */
  static getPublicKey = async (privateKey: string): Promise<string> => {
    return await UltrainApiNative.getPublicKey(privateKey);
  };
  /**
   * 对交易进行签名, 但是不发送交易
   * @param url 链地址
   * @param txInfo 交易信息
   */
  static signTx = async (
    url: UChainEndpoints,
    txInfo: TxInfo,
  ): Promise<string> => {
    let rsp: string = await UltrainApiNative.signTx(url, txInfo);
    if (rsp.startsWith('SIG_K1_')) {
      return rsp;
    }
    throw new Error('Sign Tx error: ' + rsp);
  };
}

type KeysStruct = {
  key: string;
  weight: number;
};

export type AccountInfo = {
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
  net_limit: {used: number; available: number; max: number};
  cpu_limit: {used: number; available: number; max: number};
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

export type AccountKeys = {
  [key: string]: KeysStruct[];
  active: KeysStruct[];
  owner: KeysStruct[];
};

export type CurrencyBalanceInfo = string[];

export type ChainInfo = {
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

export type TransferFeeInfo = {
  fee: string;
};

export type PushTxResult = string;

type RpcResult = {
  isSuccess: boolean;
  message: string;
  data: string;
};

function parseRpcResult(rsp: string) {
  try {
    let obj = JSON.parse(rsp) as RpcResult;
    if (obj.isSuccess === true) {
      return obj.data;
    } else {
      throw new Error(obj.message + ' : ' + obj.data);
    }
  } catch (e) {
    throw e;
  }
}

export type TxInfo = {
  contract: string; // 合约账号
  action: string; // 方法名
  account: string; // 签名账号
  privateKey: string; // 私钥
  permissionLevel: 'active' | 'owner'; // 权限级别
  params: any; // 方法的参数, 以Object的方式提供
};

export type TransferInfo = {
  from: string;
  to: string;
  quantity: string;
  memo: string;

  actor: string; // 转账发起人账号
  privateKey: string; // 转账发起人账号私钥
};

export class UChain {
  /**
   * 获取账号的信息
   * @param url 链地址
   * @param account 账号
   */
  static getAccount = async (url: UChainEndpoints, account: string) => {
    let rsp = await UltrainApiNative.getAccount(url, account);
    let data = parseRpcResult(rsp);
    return JSON.parse(data) as AccountInfo;
  };
  /**
   * 获取账号的公钥信息
   * @param url 链地址
   * @param account 账号
   */
  static getPublicKeysOfAccount = async (
    url: UChainEndpoints,
    account: string,
  ) => {
    let accountInfo = await UChain.getAccount(url, account);
    let permInfo: AccountKeys = {active: [], owner: []};
    accountInfo.permissions.forEach((p) => {
      permInfo[p.perm_name] = p.required_auth.keys;
    });
    return permInfo;
  };
  /**
   * 获取链信息
   * @param url 链地址
   */
  static getChainInfo = async (url: UChainEndpoints): Promise<ChainInfo> => {
    let rsp = await UltrainApiNative.getChainInfo(url);
    let data = parseRpcResult(rsp);
    return JSON.parse(data) as ChainInfo;
  };
  /**
   * 查询账号的余额
   * @param url 链地址
   * @param params 合约和账号信息
   */
  static getCurrencyBalance = async (
    url: UChainEndpoints,
    params: {
      account: string;
      contract: string;
      symbol: string;
    },
  ) => {
    let rsp = await UltrainApiNative.getCurrencyBalance(url, params);
    let data = parseRpcResult(rsp);
    return JSON.parse(data) as CurrencyBalanceInfo;
  };
  /**
   * 查询交易手续费
   * @param url 链地址
   * @param blockHeight 链上某个块的块高
   */
  static getTransferFee = async (url: UChainEndpoints, blockHeight: number) => {
    let rsp = await UltrainApiNative.getTransferFee(url, blockHeight);
    let data = parseRpcResult(rsp);
    return JSON.parse(data) as TransferFeeInfo;
  };
  /**
   * 发送交易
   * @param url 链地址
   * @param txInfo 交易信息
   */
  static pushTransaction = async (url: UChainEndpoints, txInfo: TxInfo) => {
    let rsp = await UltrainApiNative.pushTx(url, txInfo);
    let data = parseRpcResult(rsp);
    return JSON.parse(data) as PushTxResult;
  };
  /**
   * 转账操作
   * @param url 链地址
   * @param transferInfo 转账的信息
   */
  static transfer = async (
    url: UChainEndpoints,
    transferInfo: TransferInfo,
  ) => {
    let rsp = await UltrainApiNative.transfer(url, transferInfo);
    let data = parseRpcResult(rsp);
    return JSON.parse(data) as PushTxResult;
  };
}
