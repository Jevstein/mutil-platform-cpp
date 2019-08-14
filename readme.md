未完...

<summary>一、前言</summary>  
	这是一个工程化级别的c++组件范例，支持：  
	1.跨平台应用：linux、windows、mac、android、iOS  
	2.多语言集成：c++，java，objective-c  
	3.自动化编译：利用makefile自动根据系统平台一键编译  
	4.自动化测试：利用自动化测试工具，生成测试报告

众所周知：“千年的语言万年的c”。无论业界如何千变万化，c语言作为最接近底层的高级语言，到目前为止，从来没有跌出过排行榜的前十名。同时，c++因为其自身特性，带来诸多争议，但它的火热几十年来也一直未曾退去，况且C++新标准的出炉，更是为它带来无限活力。不可置否，在音视频开发、游戏引擎等追求高性能的领域，往往采用c或c++作为sdk组件，来支撑上层以java、c#等语言开发的应用程序。  

本工程范例，就以一个支持四则运算的计算器作为c++ sdk的简单功能，演示在linux、windows、mac、android、iOS等不同的主流系统平台下，使用c++，java，objective-c等各种不同上层语言的调用方式。即：跨平台、多语言调用c++组件。   
  
<summary>二、使用方法</summary>  
	详见doc/doc.md  

三、目录结构  
/mutil-platform-cpp  
+---/bin  
|  +---/temp  
|  |  
|  +---/win32   
|  |  +---/debug  
|  |  |  +---*.dll  
|  |  |  +---*.exe  
|  +---/android    
|  
+---/build   
|   
+---/include   
|   
+---/lib   
|  
+---/sample   
|  
+---/src   
|  