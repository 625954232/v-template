这个是可以自定义配置的模版引擎（该项目版权归帐号625954232所有，仅用于研究，不能用于其他）

支持以下语法：


列表：  
<#list var='参数名称' item='单项别名' open='语句开始符' close='语句结束符' index='索引' separator='分隔符' #>${单项属性别名}</#list#>  

判断：  
<#if text='条件' #>  
变量为真条件  
<#elseif text='条件'#>  
其他变量为真条件  
<#else#>  
变量为假条件  
</#if#>  

写值：  
<#set var='值' name='别名' #>  

渎值：  
时间格式可以格式化支持Date、LocalDateTime、时间戳  
<#get var='别名' format='时间类型格式化参数' #>  

获取值：  
${值别名}   

模版引入：  
<#include file='模版路径' #>  
