# HostelWorld 客栈服务系统
### TODO
- 酒店：
  - 登记入住时过滤：
    - 已经入住的预订单不能再次入住【入住界面输入预订订单号时提示】
    - 非本酒店的预订订单不予受理
  - 离店、记账时系统显示的未离店、未结账的订单列表 **可根据房间id搜索**
- 总经理：申请要弹出
- 可以上传图片

### 更新日志
| 日期 | 摘要 |
| :--- | :--- |
| 2017-03-20 | 身份证修改，加正则判断格式 |
| 2017-05-16 | - 房间增加【容量、数量、已预订数、空闲数、描述】属性；<br> - 客栈增加【人均消费、描述、省市】属性；<br> - 会员增加【邮箱、省、市】属性；
| 2017-05-17 | - 日期选择换了一个轻量级插件；<br> - 将预订、住店、付款订单建立外键联系
| 2017-05-18 | 如果住店的顾客有预订，输入预订单号即可生成入住单信息
| 2017-05-20 | 酒店管理员可在入住单列表那边看到每个记录是否【已支付】、是否【已离店】；<br>可在预订单列表那边看到每个记录是否【已入住】
| 2017-05-24 | 允许多人入住（多人入住同一房间）。生成一个入住单，包含多个顾客的信息，其中的每个会员都可以在自己的住店记录中查到这条完整记录；付款时也是同一个房间的多个顾客生成一个账单。<br>如果多人住一个房间，且有会员，则该会员的累计消费金额增加（房间折后价/人数）；本次房间的折扣按本房间会员中折扣最低的人算
| 2017-05-24 | 离店、记账时系统自动显示未离店、未结账的订单列表可供选择

### j2ee大作业
- springMVC框架搭建

### 本系统使用者有三种角色。
 - 会员
   - 预订房间
   - 享受会员优惠：折扣、积分换钱
   - 管理个人信息：昵称、手机号、年龄、生日、密码
   - 查看住店信息：查看预订记录、住店离店记录
 - 酒店经理
   - 发布房间计划
   - 登记入店、离店、支付记录
   - 查看本店的统计数据
 - 网站总经理
   - 审批酒店开店申请
   - 将收入结算给各个酒店
   - 查看整个网站的统计数据(按客栈、按会员)
 第一次自己好好认真的写完一个项目~会不定期维护的！^ ^
