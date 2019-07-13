* 资源跨域 
* 同源策略SOP（Same origin policy）：是一种约定，由Netscape公司1995年引入浏览器，它是浏览器最核心也最基本的安全功能，如果缺少了同源策略，浏览器很容易受到XSS、CSFR等攻击。所谓同源是指"协议+域名+端口"三者相同，即便两个不同的域名指向同一个ip地址，也非同源。
- 解决方案
    - 通过jsonp跨域
    - document.domain + iframe跨域
    -  location.hash + iframe
    - window.name + iframe跨域
    - postMessage跨域
    - 跨域资源共享（CORS）
    - nginx代理跨域
    - nodejs中间件代理跨域
    - WebSocket协议跨域
    
* Access-Control-Allow-Origin
该字段是必须的。它的值要么是请求时Origin字段的值，要么是一个*，表示接受任意域名的请求。

* Access-Control-Allow-Credentials
该字段可选。它的值是一个布尔值，表示是否允许发送Cookie。默认情况下，Cookie不包括在CORS请求之中。设为true，即表示服务器明确许可，Cookie可以包含在请求中，一起发给服务器。这个值也只能设为true，如果服务器不要浏览器发送Cookie，删除该字段即可。

* Access-Control-Expose-Headers
该字段可选。CORS请求时，XMLHttpRequest对象的getResponseHeader()方法只能拿到6个基本字段：Cache-Control、Content-Language、Content-Type、Expires、Last-Modified、Pragma。如果想拿到其他字段，就必须在Access-Control-Expose-Headers里面指定。上面的例子指定，getResponseHeader('FooBar')可以返回FooBar字段的值。

* withCredentials 属性
上面说到，CORS请求默认不发送Cookie和HTTP认证信息。如果要把Cookie发到服务器，一方面要服务器同意，指定Access-Control-Allow-Credentials字段。
Access-Control-Allow-Credentials: true
另一方面，开发者必须在AJAX请求中打开withCredentials属性。
var xhr = new XMLHttpRequest();
xhr.withCredentials = true;
否则，即使服务器同意发送Cookie，浏览器也不会发送。或者，服务器要求设置Cookie，浏览器也不会处理。
但是，如果省略withCredentials设置，有的浏览器还是会一起发送Cookie。这时，可以显式关闭withCredentials。
xhr.withCredentials = false;
需要注意的是，如果要发送Cookie，Access-Control-Allow-Origin就不能设为星号，必须指定明确的、与请求网页一致的域名。同时，Cookie依然遵循同源政策，只有用服务器域名设置的Cookie才会上传，其他域名的Cookie并不会上传，且（跨源）原网页代码中的document.cookie也无法读取服务器域名下的Cookie。

* XMLHttpRequest.withCredentials 有什么用?
跨域请求是否提供凭据信息(cookie、HTTP认证及客户端SSL证明等)
也可以简单的理解为，当前请求为跨域类型时是否在请求中协带cookie。
XMLHttpRequest.withCredentials 怎么用?
withCredentials属于XMLHttpRequest对象下的属性，可以对其进行查看或配置
withCredentials: true,// 指定某个请求应该发送凭据。允许客户端携带跨域cookie，也需要此配置

* Referrer Policy 规定了五种 Referrer 策略：No Referrer、No Referrer When Downgrade、Origin Only、Origin When Cross-origin、和 Unsafe URL。
五种类型的具体含义：
No Referrer：任何情况下都不发送 Referrer 信息；
No Referrer When Downgrade：仅当发生协议降级（如 HTTPS 页面引入 HTTP 资源，从 HTTPS 页面跳到 HTTP 等）时不发送 Referrer 信息。这个规则是现在大部分浏览器默认所采用的；
Origin Only：发送只包含 host 部分的 Referrer。启用这个规则，无论是否发生协议降级，无论是本站链接还是站外链接，都会发送 Referrer 信息，但是只包含协议 + host 部分（不包含具体的路径及参数等信息）；
Origin When Cross-origin：仅在发生跨域访问时发送只包含 host 的 Referrer，同域下还是完整的。它与 Origin Only 的区别是多判断了是否 Cross-origin。需要注意的是协议、域名和端口都一致，才会被浏览器认为是同域；
Unsafe URL：无论是否发生协议降级，无论是本站链接还是站外链接，统统都发送 Referrer 信息。正如其名，这是最宽松而最不安全的策略

## 预检请求
### 一、问题
我的项目需求是写一个 App 版本管理器，前后端都由我开发。前端分为两个部分：运营人员写版本更新说明的内部系统和 App 访问的产品页；后端就是对 App 版本进行管理的 CURD 接口。重点在于三个部分的程序部署在三台服务器上，前端的两个系统在不同的服务器对第三个服务器上的接口进行数据请求，这就不可避免的涉及到了跨域。

当然，只是跨域的话也不难解决，添加 Access-Control-Allow-Origin 为要跨域的域名就 OK 了，或者直接赋值为 *。但是我的部分接口涉及鉴权，通过 JWT 进行校验，如果 JWT 不合法，那么会返回 401 Unauthorized 错误；而我的 JWT 是通过请求头的自定义字段 authorization 带到服务器的，这就导致一个更加麻烦的问题出现了 —— 预检请求。

### 二、收获
* 什么是预检请求？
预检请求（preflight request），是一个跨域请求，用来校验当前跨域请求能否被理解。

它使用 HTTP 的 OPTIONS 请求，一般会包括一下请求头：Access-Control-Request-Method，Access-Control-Request-Headers 和 Origin。

预检请求通常在必要的时候由浏览器自动发起，不需要程序员进行干预。

如果我们想要知道服务器是否支持一个 DELETE 请求，在发送 DELETE 请求之前，服务器通常会发送一个如下的预检请求：

OPTIONS /resource/foo 
Access-Control-Request-Method: DELETE 
Access-Control-Request-Headers: origin, x-requested-with
Origin: https://foo.bar.org
如果服务器允许使用 DELETE 方法的话，会返回如下响应头；其中 Access-Control-Allow-Methods 会列出 DELETE 方法，代表服务器支持这个方法。
HTTP/1.1 200 OK
Content-Length: 0
Connection: keep-alive
Access-Control-Allow-Origin: https://foo.bar.org
Access-Control-Allow-Methods: POST, GET, OPTIONS, DELETE
Access-Control-Max-Age: 86400
**由此可知，预检请求是一个用于校验服务器是否支持当前方法以及是否能够理解当前请求的一种请求，它区别于一般的请求，不由代码发起，而在必要的时候由浏览器自动发出。**

* 预检请求与普通请求的区别
满足以下条件的请求就是简单请求：
    * 请求方法属于下面三种方法之一：
    HEAD
    POST
    GET
    * HTTP 的请求头信息超出一下范围：
    Accept
    Accept-Language
    Content-Language
    Last-Event-ID
    * Content-Type：超出这三个的范围：
    application/x-www-form-urlencoded
    multipart/form-data
    text/plain
不满足以上条件的请求就是非简单请求。

* 如果是简单的 CORS 请求，浏览器会自动在请求头中添加一个 Origin 请求头字段，如果响应头对应的 Access-Control-Allow-Origin 没有包含 Origin 所指定的域，那么就会报 CORS 错误，请求失败。所以服务器的响应要添加对应的响应头。

* 如果是非简单的 CORS 请求，那么会有一次预检请求，在正是请求之前发出一个 OPTIONS 请求对服务器进行检测。
  * 除了有 Origin 以外，预检请求的请求头还包括一下两个特殊字段：
  Access-Control-Request-Method：表示 CORS 请求要用到的请求方法。
  Access-Control-Request-Headers：这是一个用逗号分割的字符串，指出 CORS 请求要附加的请求头。
  
  * 服务器的响应可以包含以下字段：
  Access-Control-Allow-Methods：逗号分割的字符串，表示允许的跨域请求方法。
  
  比如：
  Access-Control-Allow-Methods: PUT, POST, GET, OPTIONS
  Access-Control-Allow-Headers：如果浏览器请求包含 Access-Control-Request-Headers 字段，那么服务器中该响应头也是必须的，也是一个由逗号分隔的字符串，表示服务器支持的请求头。
  
  比如：
  Access-Control-Allow-Headers: authorization
  Access-Control-Max-Age：可选字段，设置当前预检请求的有效期，单位为秒。
  Access-Control-Allow-Credentials：可选字段。默认情况下，CORS 请求不携带 cookie，如果服务器想要 cookie，需要指定该请求头为 true。


### 三、解决方法
避免出现预检请求，需要使得你的请求满足简单请求的两个条件。
比如在使用 JWT 鉴权时，可能会把你的 token 放在请求头的 authorization 字段，因为这个字段超出了简单请求的范围，所以请求会变成非简单请求。这时可以不把 token 放在 authorization 请求头中。
出现预检请求后，进行服务器配置，分别设置好 Access-Control-Allow-Origin、Access-Control-Allow-Methods 和 Access-Control-Allow-Headers，使得你的非简单请求能够通过预检请求。
如果使用 Hapijs 的话，只需要在路由配置中增加 cors: true 配置即可。

COOKIE和SESSION有什么区别？
cookie保存在客户端，session保存在服务器端，
cookie目的可以跟踪会话，也可以保存用户喜好或者保存用户名密码
session用来跟踪会话

1. 由于HTTP协议是无状态的协议，所以服务端需要记录用户的状态时，就需要用某种机制来识具体的用户，这个机制就是Session.典型的场景比如购物车，当你点击下单按钮时，由于HTTP协议无状态，所以并不知道是哪个用户操作的，所以服务端要为特定的用户创建了特定的Session，用用于标识这个用户，并且跟踪用户，这样才知道购物车里面有几本书。这个Session是保存在服务端的，有一个唯一标识。在服务端保存Session的方法很多，内存、数据库、文件都有。集群的时候也要考虑Session的转移，在大型的网站，一般会有专门的Session服务器集群，用来保存用户会话，这个时候 Session 信息都是放在内存的，使用一些缓存服务比如Memcached之类的来放 Session。

2. 思考一下服务端如何识别特定的客户？这个时候Cookie就登场了。每次HTTP请求的时候，客户端都会发送相应的Cookie信息到服务端。实际上大多数的应用都是用 Cookie 来实现Session跟踪的，第一次创建Session的时候，服务端会在HTTP协议中告诉客户端，需要在 Cookie 里面记录一个Session ID，以后每次请求把这个会话ID发送到服务器，我就知道你是谁了。有人问，如果客户端的浏览器禁用了 Cookie 怎么办？一般这种情况下，会使用一种叫做URL重写的技术来进行会话跟踪，即每次HTTP交互，URL后面都会被附加上一个诸如 sid=xxxxx 这样的参数，服务端据此来识别用户。

3. Cookie其实还可以用在一些方便用户的场景下，设想你某次登陆过一个网站，下次登录的时候不想再次输入账号了，怎么办？这个信息可以写到Cookie里面，访问网站的时候，网站页面的脚本可以读取这个信息，就自动帮你把用户名给填了，能够方便一下用户。这也是Cookie名称的由来，给用户的一点甜头。所以，总结一下：Session是在服务端保存的一个数据结构，用来跟踪用户的状态，这个数据可以保存在集群、数据库、文件中；Cookie是客户端保存用户信息的一种机制，用来记录用户的一些信息，也是实现Session的一种方式。

* token的构造 token是由header（头）、payload（荷载）、sign（签名）三部分组成。
    
    * header：头部信息主要包括（参数的类型--JWT,签名的算法--HS256）
    
    * payload：存放自己想要的信息    
    
    * sign：是为了防止恶意篡改数据
    
* 什么是JWT
Json web token (JWT), 是为了在网络应用环境间传递声明而执行的一种基于JSON的开放标准（(RFC 7519).定义了一种简洁的，自包含的方法用于通信双方之间以JSON对象的形式安全的传递信息。因为数字签名的存在，这些信息是可信的，JWT可以使用HMAC算法或者是RSA的公私秘钥对进行签名。

RESTful API认证方式
一般来讲，对于RESTful API都会有认证(Authentication)和授权(Authorization)过程，保证API的安全性。

Authentication vs. Authorization

Authentication指的是确定这个用户的身份，Authorization是确定该用户拥有什么操作权限。

认证方式一般有三种

Basic Authentication

这种方式是直接将用户名和密码放到Header中，使用Authorization: Basic Zm9vOmJhcg==，使用最简单但是最不安全。

TOKEN认证

这种方式也是再HTTP头中，使用Authorization: Bearer <token>，使用最广泛的TOKEN是JWT，通过签名过的TOKEN。

OAuth2.0

这种方式安全等级最高，但是也是最复杂的。如果不是大型API平台或者需要给第三方APP使用的，没必要整这么复杂。

一般项目中的RESTful API使用JWT来做认证就足够了

所谓加Salt，就是加点“佐料”。当用户首次提供密码时（通常是注册时），由系统自动往这个密码里加一些“Salt值”，这个值是由系统随机生成的，并且只有系统知道。然后再散列。而当用户登录时，系统为用户提供的代码撒上同样的“Salt值”，然后散列，再比较散列值，已确定密码是否正确。 　　

这样，即便两个用户使用了同一个密码，由于系统为它们生成的salt值不同，他们的散列值也是不同的。即便黑客可以通过自己的密码和自己生成的散列值来找具有特定密码的用户，但这个几率太小了（密码和salt值都得和黑客使用的一样才行）。
假设你的数据库已经被黑客拿到了。
比如 md5sum("123456") = "e10adc3949ba59abbe56e057f20f883e"
如果不用 salt ，直接 md5 的话，直接在你的数据库里搜索后面那个值不就能找出所有密码是"123456"的用户了吗？
要知道，黑客的目的其实并不是（或者说大多数时候不是）破解某一个人的密码，而是获取大量的用户名/密码对。
好了，现在你加了盐，但盐是固定的，比如直接写死在代码里的，这样行不行呢？这样也是不行的，因为黑客仍然可以通过预先计算的方式来做。比如黑客黑了你的服务器，看到了你的代码，知道了你的盐是 xxx ，于是他计算：
md5sum("123456xxx") = "e087dae60e744ea80722b785a75adbb7"
然后再到你的数据库里搜索，又能得到大量密码是 123456 的用户了。

那该怎么办呢？
所以，不但要加盐，而且每个用户的盐还得不一样。我看到的一种做法是，直接用每个用户的用户名，或者用户名的变形来做盐。

* 比如在Web页面的cookie里，localstorage里等地方存储了名文的密码，并且被XSS了。

* 又或者app将密码存到了沙箱的文件系统，但是很不幸app被篡改了，或者手机彻底被root了。应对方案是，前端压根就不要保存密码，存到后端去；又或者使用http only cookie这种技术，不允许前端代码触碰密码数据；又者保证前端存在某种类似于iOS的keychain这种机制做存储。

* 密码输入被盗走。用户通过UI的控件输入密码，在还没传输给服务器之前，攻击者盗走。这时需要前端加密。但我们特别留意下，如果传输因为各种原因无法使用SSL，或者走客户端代理，而代理者拥有SSL的私钥（比如大型企业内部的代理用户监控员工行为），那么客户端加密就非常非常的重要了。密码在传输时被别人盗走。这个大家都有共识了，上SSL（对于http就是https了）。如果特定领域因为某种原因无上ssl，就必须弄一个大概等价于SSL的简化方案。本质上这个传输就是一边加密，一边解密，同时保证密钥本身不会在传输时泄漏。此外，https的一个额外用处是将证书和域名结合起来，而域名又被DNS控制。这仨在一起可以避免钓鱼攻击。

* 密码在服务器被别人盗走 ,密码存在数据库里，然后被拖库。应对方法是，内网防范——避免数据库被攻破。但谁也不能打这个包票。因此更好的办法是，压根就不要存原始的明文密码，甚至不能存储可以很容易被反向回明文的密码hash。常用的技术是对每一个密码加不同的盐。高仿真钓鱼。如果靠直接在浏览器里输入url的方式进入某个网站其实很不靠谱的。钓鱼网站很容易让自己长得非常像真的网站（包括域名在内），并且证书也相对容易做不经任何审查的申请。因此，尽量从搜索引擎等地方来进入。由这些入口来保证你进入的真的是你想进入的那个网站。在移动端，这个入口一般是app store保证的。app store保证给你的app是正牌的，没篡改过的，非李鬼的app。社会工程学。冒名顶替打电话给一个想攻破的人要密码。花言巧语，总能骗到一些人。安全是一个整体，任何地方出漏洞整个体系就出问题。

* 此外，安全是分级别的，比如工具类服务安全性可以要求稍微低一些，于是做好前两个能解决大部分问题。而金融类服务就要所有都做才行。

* 在上面这个体系下，可以看到“前端加密”的重要性和是否能使用安全的传输关系很大。如果能使用https，前端加密起的作用就是用户在前端输入密码，然后进入https传输之前的一个加密。如果能经常换换密钥，并且加密是单向的，尽管能被人看到，也是“有意义的”，只不过这个意义从全局看很小而已。简单说就是性价比不高。但如果不能使用安全的传输，前端加密其实起到的就是安全传输的作用。要进行前端加密比较好的方式是，用户一输入密码，就用公钥加密，然后再用传输，然后服务器用私钥解密，之后在加盐存储。正如上文，这其实是在仿照ssl。另外一种方式是，对用户密码在前端hash，然后传输到后端后再第这个hash做一次加盐。但是hash使用的算法相对容易破解，如MD5，SHA1等。