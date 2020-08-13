//
//  AbiBinargs.swift
//  eosswift
//
//  Created by fanliangqin on 2020/7/22.
//  Copyright © 2020 memtrip. All rights reserved.
//

import Foundation

public struct AbiBinargs: Codable {
    public let binargs: String
    
    public init(binargs: String) {
        self.binargs = binargs
    }
}
