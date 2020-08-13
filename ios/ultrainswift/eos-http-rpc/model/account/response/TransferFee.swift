//
//  TransferFee.swift
//  BigInt
//
//  Created by fanliangqin on 2020/7/22.
//

import Foundation

public struct TransferFee : Codable {
    public let fee: String

    public init(fee: String) {
        self.fee = fee
    }
}
