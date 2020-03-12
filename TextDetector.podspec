
  Pod::Spec.new do |s|
    s.name = 'TextDetector'
    s.version = '0.0.1'
    s.summary = 'text detector'
    s.license = 'Apache 2'
    s.homepage = 'text-detector'
    s.author = 'Vennela Kodali'
    s.source = { :git => 'text-detector', :tag => s.version.to_s }
    s.source_files = 'ios/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}'
    s.ios.deployment_target  = '11.0'
    s.dependency 'Capacitor'
  end