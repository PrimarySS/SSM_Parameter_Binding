# SSM_Parameter_Binding
ssm参数绑定（简单，数组，list，map)

Controller默认支持的参数类型有四个

HttpServletRequest 通过request对象获取请求信息

HttpServletResponse  通过response处理响应信息

HttpSession  通过session对象得到session中存放的对象

Model/ ModelMap  将模型数据填充到request域 model是一个接口modelMap是一个接口实现

本次项目中只介绍了HttpServletRequest，通过HttpServletRequest阐述了简单数据类型绑定，整型、字符串、日期...，pojo类型绑定，集合类型绑定和自定义参数绑定的过程。

详细的项目过程可以参考本博主的csdn相关文章https://blog.csdn.net/qq_38931949/article/details/83446689
