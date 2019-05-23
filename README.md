# javaAgent-AG
目前不支持spring管理的bean


premain 是在启动时加载每个class文件修改字节码  ，所以spring管理的bean也能生效
agentmain 是在运行时候修改每个class文件修改字节码  ，所以spring的singleton管理的bean不能生效，
