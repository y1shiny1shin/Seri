```plant
如果某个可序列化的类重写了readObject()方法，反序列化时就优先调用重写后的方法

yso 中大概包含如下几种可用的常见调用链：
1. InvokerTransformer 调用任意方法（依赖 CC）
2. BeanComparator 调用 getter 方法（依赖 CB）
3. BasicPropertyAccessor$BasicGetter 调用 getter 方法(依赖 Hibernate)
4. MemberBox 反射调用任意方法（依赖 rhino）
5. ToStringBean 调用全部 getter 方法（依赖 Rome）
6. MethodInvokeTypeProvider 反射调用任意方法（依赖 spring-core）


```

```text
1. Shiro1.5.3 和 1.2.4的加密算法不同，shiro1.2.4搓的链子不能在1.5.3的版本下反序列化成功
2. CB链在1.9.4和1.9.2均可以使用
```
