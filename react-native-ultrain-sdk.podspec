require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "react-native-ultrain-sdk"
  s.ios.deployment_target  = '9.0'
  s.swift_version          = '5.0'
  s.version      = package["version"]
  s.summary      = package["description"]
  s.description  = <<-DESC
                  react-native-ultrain-sdk
                   DESC
  s.homepage     = "https://github.com/github_account/react-native-ultrain-sdk"
  # brief license entry:
  s.license      = "MIT"
  # optional - use expanded license entry instead:
  # s.license    = { :type => "MIT", :file => "LICENSE" }
  s.authors      = { "Your Name" => "yourname@email.com" }
  s.platforms    = { :ios => "9.0" }
  s.source       = { :git => "https://github.com/github_account/react-native-ultrain-sdk.git", :tag => "#{s.version}" }

  s.source_files = "ios/**/*.{h,c,m,swift}"

  s.pod_target_xcconfig    = {'SWIFT_INCLUDE_PATHS' => '$(SRCROOT)/ios/**', 'LIBRARY_SEARCH_PATHS' => '$(SRCROOT)/ios/'}
  # s.preserve_paths         = 'microecc/module.modulemap'
  s.header_dir             = 'microecc'
  s.public_header_files    = 'microecc/*.h'

  s.requires_arc = true

  s.dependency "React"
  # ...
  s.dependency               'RxSwift','~> 4.0'
  s.dependency               'RxCocoa', '~> 4.0'
  s.dependency               'BigInt', '~> 3.1.0'
end

