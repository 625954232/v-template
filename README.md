# nabi-parent

#### 介绍
nabi-template是一个用Java语言编写的模板引擎，它基于HTML模板来生成文本输出。若使用于文档配置可以使用该引擎。它支持自定义语法标签、根据MAP和实体类生成语法、生成预览。
 


#### 配置说明
 1、主配置（JoinConfig）

| 属性名 | 说明 |
| ----- | ------- |
| encoding | 模版编码 |
| resourcesDir | 资源目录 |
| cacheType | 缓存类型 |
| cacheSize | 缓存大小（SINGLE 单例， MAP 字典存储模式） |

2、类型转换配置（ClassConvertConfig） 

| 属性名 | 说明 |
| ----- | ------- |
| decimalScale | 小数精确位数，默认为2 |
| dateFormat | 日期类型格式化参数，默认为yyyy-MM-dd |
| timeFormat | 时间类型格式化参数，默认为HH:mm:ss |
| dateTimeFormat | 日期时间类型格式化参数，默认为yyyy-MM-dd HH:mm:ss |

3、表达式配置（ExpressionConfig）

| 属性名 | 说明 |
| ----- | ------- |
| varTagStart | 值表达式截取开始标记 |
| varTagEnd | 值表达式截取结束标记 |
| exprFirstBegin | 语法表达式开始标签首部截取标记 |
| exprLastBegin | 语法表达式结束标签首部截取标记 |
| exprEndSupport | 表达式开始标签或结束标签尾部截取标记 |
| attStart | 属性截取开始符 |
| attEnd | 属性截取结束符 |
| attSseparator | 分隔符属性 |
| attVar | 值属性 |
| attName | 别名属性 |
| attItem | 单项别名属性 |
| attOpen | 语句开始属性 |
| attIndex | 索引属性 |
| attClose | 语句结束属性 |
| attFile | 引用文件属性 |
| attrText | 判断条件属性 |
| attrFormat | 时间格式化属性 |

#### 原理解释
![模板引擎原理图](static/模板引擎原理图.png)

#### 语法说明
一、循环语法
```
<#list var='参数名称' item='单项别名' open='语句开始符' close='语句结束符' index='索引' separator='分隔符' #>
    有数据时生成的内容
<#else#>
    没有有数据时生成的内容
</#list#>
```
属性介绍：

1、分隔符：用于隔开多条语句的一个符号，比如你填写的分隔符为separator("，")，那么产生的效果是“你很帅, 你很帅, 你很帅”

2、开始语句：用于显示在多条语句起始部分的语句，比如你填写的开始语句为open("你好，知道吗？")，那么产生的效果是“你好，知道吗？你很帅”

3、结束语句：用于显示在多条语句结束的语句，比如你填写的结束语句为close("你真的很帅") ，那么产生的效果是“你很帅你真的很帅”

4、语句内容：当“请在填写有数据要生成内容”有这句话的地方，就要替换成填写有数据的时候要生成的内容；当“请在填写没有数据要生成内容”有这句话的地方，就要替换成填写没有数据的时候要生成的内。系统会选择生成有数据或没有数据要生成的内容

5、换行和不换行： 当循环语句在同一行的时候是不会换行的，如果循环语法的起始标签、内容、结束标签分别占一行时，循环的语句就会换行

二、判断语法
```
<#if text='条件' #>
变量为真条件
<#elseif text='条件'#>
其他变量为真条件
<#else#>
变量为假条件
</#if#>
```
判断语法的条件表达式执行器是使用apache的jexl，可以按照jexl的语法来设置你的判断条件

三、写值：

```
<#set var='值' name='别名' #>
```

四、渎值：

```
<#get var='别名' format='时间类型格式化参数' #>
```
时间格式可以格式化支持Date、LocalDateTime、时间戳


五、获取值：

```
${值别名}
```

六、模版引入：

```
<#include file='模版路径' #>
```
#### 使用说明

一、模版转换

      HtmlJoinFactoryBuilder joinFactoryBuilder = new HtmlJoinFactoryBuilder();
      JoinFactory joinFactory = joinFactoryBuilder.build();  //构建模版工厂
      Template template = joinFactory.getTemplate("/preview.html"); //获取模版
      template.putValue("参数", "值");   //添加参数
      template.putContext( )    //设置参数
      String process = template.process();

二、生成字段

       HtmlJoinFactoryBuilder joinFactoryBuilder = new HtmlJoinFactoryBuilder();
       JoinFactory joinFactory = joinFactoryBuilder.build();
       Generate grammarGenerate = joinFactory.createGenerate(Map.class);
       grammarGenerate.setGenerateListener("生成监听");
       grammarGenerate.setGenerateConfig("参数字段属性名称配置");
       grammarGenerate.generate("MAP结构的M参数或Bean实体");
       List<GrammarInfo> list = grammarGenerate.getGrammarInfos();
       System.out.println(list);

三：预览

       HtmlJoinFactoryBuilder joinFactoryBuilder = new HtmlJoinFactoryBuilder();
       JoinFactory joinFactory = joinFactoryBuilder.build();
       Generate grammarGenerate = joinFactory.createGenerate(Map.class);
       grammarGenerate.setGenerateListener("生成监听");
       grammarGenerate.setGenerateConfig("参数字段属性名称配置");
       grammarGenerate.generate("MAP结构的M参数或Bean实体");
       String preview=  grammarGenerate.preview("HTML模版", "循环次数")
       System.out.println(preview);
       
#### 使用技术
1、[hutool工具集](https://hutool.cn/docs/#/)

2、[Java表达式语言](http://commons.apache.org/proper/commons-jexl/)
