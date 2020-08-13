//
//  BlockHeight.swift
//  BigInt
//
//  Created by fanliangqin on 2020/7/22.
//

import Foundation

public struct BlockHeight: Encodable {
    public let block_height: UInt64
    
    public init(block_height: UInt64) {
        self.block_height = block_height
    }
}
