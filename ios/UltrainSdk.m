//
//  UltrainSdk.m
//  UltrainSdk
//
//  Created by fanliangqin on 2020/8/13.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(UltrainSdkModule, NSObject)

RCT_EXTERN_METHOD(sha256:(NSString *)rawStr resolver:(RCTPromiseResolveBlock)resolve rejector:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(seedPrivate:(NSString *)seeds resolver:(RCTPromiseResolveBlock)resolve rejector:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(generatePrivate:(RCTPromiseResolveBlock)resolve rejector:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(getPublicKey:(NSString *)privateKey resolver:(RCTPromiseResolveBlock)resolve rejector:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(signTx:(NSString *)url txInfo:(NSDictionary *)txInfo resolver:(RCTPromiseResolveBlock)resolve rejector:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(getAccount:(NSString *)url account:(NSString *)account resolver:(RCTPromiseResolveBlock)resolve rejector:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(getChainInfo:(NSString *)url resolver:(RCTPromiseResolveBlock)resolve rejector:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(getCurrencyBalance:(NSString *)url params:(NSDictionary *)params resolver:(RCTPromiseResolveBlock)resolve rejector:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(getTransferFee:(NSString *)url blockHeight:(NSInteger *)blockHeight resolver:(RCTPromiseResolveBlock)resolve rejector:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(pushTx:(NSString *)url txInfo:(NSDictionary *)txInfo resolver:(RCTPromiseResolveBlock)resolve rejector:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(transfer:(NSString *)url params:(NSDictionary *)params resolver:(RCTPromiseResolveBlock)resolve rejector:(RCTPromiseRejectBlock)reject)
@end
