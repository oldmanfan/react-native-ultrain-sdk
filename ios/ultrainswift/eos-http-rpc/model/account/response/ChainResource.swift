//
//  ChainResource.swift
//  BigInt
//
//  Created by fanliangqin on 2020/7/21.
//

import Foundation

public struct ChainResource : Codable {
    public let chain_name: String
    public let lease_num: Int64
    public let locked_num: Int64
    public let start_time: Date
    public let end_time: Date

    public init(chain_name: String, lease_num: Int64, locked_num: Int64, start_time: Date, end_time: Date) {
        self.chain_name = chain_name
        self.lease_num = lease_num
        self.locked_num = locked_num
        self.start_time = start_time
        self.end_time = end_time
    }
}
