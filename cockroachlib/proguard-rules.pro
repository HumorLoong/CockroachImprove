-optimizationpasses 5                                                           # 指定代码的压缩级别  
-dontusemixedcaseclassnames                                                     # 是否使用大小写混合  
-dontskipnonpubliclibraryclasses                                                # 是否混淆第三方jar  
-dontpreverify                                                                  # 混淆时是否做预校验  
-verbose                                                                        # 混淆时是否记录日志  
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*        # 混淆时所采用的算法

-keep class com.talkweb.lxl.cockroachlib.CockroachImprove{*;}
-keep class com.talkweb.lxl.cockroachlib.CrashErrorUtil{*;}
-keep class com.talkweb.lxl.cockroachlib.EmptyActivity{*;}
-keep class com.talkweb.lxl.cockroachlib.ErrorActivity{*;}
-keep class com.talkweb.lxl.cockroachlib.ExceptionHandler{*;}