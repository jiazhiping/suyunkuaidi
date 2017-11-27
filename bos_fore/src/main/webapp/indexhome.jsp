<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>速运快递</title>
		<link rel="stylesheet" type="text/css" href="plugins/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/public.css">
		<link rel="stylesheet" type="text/css" href="css/nav.css">
		<link rel="stylesheet" type="text/css" href="css/styleindex.css">

		<script src="plugins/jquery.min.js" type="text/javascript"></script>
		<script src="plugins/bootstrap.min.js" type="text/javascript"></script>
		<script src="js/nav.js" type="text/javascript"></script>
		
		<!-- 客服引用jar -->
		<LINK rel=stylesheet type=text/css href="${pageContext.request.contextPath}/js/kefuqq/css/common.css" />
		<SCRIPT type=text/javascript src="${pageContext.request.contextPath}/js/kefuqq/js/jquery.js"></SCRIPT>
		<SCRIPT type=text/javascript src="${pageContext.request.contextPath}/js/kefuqq/js/kefu.js"></SCRIPT>

</head>
<body>
	<!-- 1.城市天气预报 -->
	<span><iframe name="sinaWeatherTool" src="http://weather.news.sina.com.cn/chajian/iframe/weatherStyle2.html?city=%E5%8C%97%E4%BA%AC" width="200" height="20" marginwidth="0" marginheight="0" hspace="0" vspace="0" frameborder="0" scrolling="no"></iframe></span>
	<!-- 2.鼠标特效 -->
	<script src="js/canvas-nest.min.js" color="55,166,236" zIndex="-2" count="400" type="text/javascript" charset="utf-8"></script>
	<!-- 3.在线客服              start -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center">
				<DIV id=floatTools class=float0831 style="height: 100px">
					<DIV class=floatL>
						<A style="DISPLAY: none" id=aFloatTools_Show class=btnOpen
							title=查看在线客服
							onclick="javascript:$('#divFloatToolsView').animate({width: 'show', opacity: 'show'}, 'normal',function(){ $('#divFloatToolsView').show();kf_setCookie('RightFloatShown', 0, '', '/', 'dd'); });$('#aFloatTools_Show').attr('style','display:none');$('#aFloatTools_Hide').attr('style','display:block');"
							href="javascript:void(0);">展开</A> <A id=aFloatTools_Hide
							class=btnCtn title=关闭在线客服
							onclick="javascript:$('#divFloatToolsView').animate({width: 'hide', opacity: 'hide'}, 'normal',function(){ $('#divFloatToolsView').hide();kf_setCookie('RightFloatShown', 1, '', '/', 'dd'); });$('#aFloatTools_Show').attr('style','display:block');$('#aFloatTools_Hide').attr('style','display:none');"
							href="javascript:void(0);">收缩</A>
					</DIV>
					<DIV id=divFloatToolsView class=floatR style="display: none">
						<DIV class=tp></DIV>
						<DIV class=cn>
							<UL>
								<LI class=top>
									<H3 class=titZx>QQ咨询</H3>
								</LI>
								<LI><A class=icoTc href="tencent://message/?uin=554007167Site=sc.chinaz.com&Menu=yes">联系管理员</A></LI>
							</UL>
							<UL class=webZx>
								<LI class=webZx-in><A href="#" target="_blank"
									style="FLOAT: left"><IMG
										src="${pageContext.request.contextPath}/js/kefuqq/images/right_float_web.png"
										border="0px" /></A></LI>
							</UL>
							<UL>
								<LI>
									<H3 class=titDh>电话咨询</H3>
								</LI>
								<LI><SPAN class=icoTl>138-383-8438</SPAN></LI>
							</UL>
						</DIV>
					</DIV>
				</DIV></td>
		</tr>
	</table>
	<!-- 3.在线客服              end -->
	
		<!--nav-->
		<div id="headnav">
			<div class="mainnav">
				<nav class="nav navbar-default">
					<div class="container">
						<div class="navbar-header">
								<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
						                    <span class="sr-only">折叠菜单</span>
						                    <span class="icon-bar"></span>
						                    <span class="icon-bar"></span>
						                    <span class="icon-bar"></span>
			               				</button>
								<a class="navbar-brand" href="#"><img src="img/icon/logo.png"></a>
								<br>
								</div>
							<div class="collapse navbar-collapse" >
						<ul class="nav navbar-nav navbar-right" id="menu">
							<li>
								<a href="index.html" target="_blank">首页</a>
							</li>
							<li>
								<a href="order.html" target="_blank">在线下单</a>
							</li>
							<li>
								<a href="express_manage.html" target="_blank">快件管理</a>
							</li>
							<li>
								<a href="userinfo.html" target="_blank">账户管理</a>
							</li>
							<li>
								<a href="search.html" target="_blank">查询服务</a>
							</li>
							<li>
								<a href="myhome.html" target="_blank">我的主页</a>
							</li>
							<li>
								<a href="login.html" target="_blank">登录</a>
							</li>
						</ul>
						</div>
					</div>
				</nav>
			</div>
		</div>
		
		<section class="bannerarea">
						
			<div class="bannerimg"><img src="img/show/suyun/banner.png" class="img-responsive" alt="Responsive image"></div>
			<div class="seekimport">
				<div class="container">
					<ul class="list-inline">
						<li class="active">
							<img src="img/icon/suyun/icon1.png" alt="point1" />
							<p>运单追踪</p>
						</li>
						<li>
							<img src="img/icon/suyun/icon2.png" alt="point2" />
							<p>运费时效查询</p>
						</li>
						<li>
							<img src="img/icon/suyun/icon2.png" alt="point2" />
							<p>服务网点查询</p>
						</li>
						<li>
							<img src="img/icon/suyun/icon2.png" alt="point2" />
							<p>收送范围查询</p>
						</li>
						<li>
							<img src="img/icon/suyun/icon2.png" alt="point2" />
							<p>在线客服</p>
						</li>
					</ul>
				</div>
			</div>
		</section>
		<!-- promotion area-->
		<section class="promotion">
			<div class="container">
				<div class="areatitle">
					<h2 class="text-center"><span class="maintitle">活动促销</span></h2>
					<p class="text-center"><span class="subtitle">ACTIVITY PROMOTION</span></p>
				</div>

				<div class="row">
					<div class="col-sm-6 col-md-3">
						<div class="thumbnail">
							<img src="img/show/suyun/promotion1.png" alt="活动一">

							<div class="caption">
								<p>进入x月，中俄跨境电商物流服务又添 喜讯：速运快递特惠开通了中国大陆...
								</p>

								<p class="text-right status"><span>进行中</span></p>
								<p class="text-right grey">20xx.xx.xx-20xx.xx.xx</p>
								<p class="text-right grey">中国大陆适用</p>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="thumbnail">
							<img src="img/show/suyun/promotion2.png" alt="活动二">

							<div class="caption">
								<p>疯狂夏寄，速运三重惠，给利益享不停 手机支付运费返5%
								</p>

								<p class="text-right status"><span>进行中</span></p>
								<p class="text-right grey">20xx.xx.xx-20xx.xx.xx</p>
								<p class="text-right grey">中国大陆适用</p>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="thumbnail">
							<img src="img/show/suyun/promotion3.png" alt="活动三">

							<div class="caption">
								<p>速运快递再开通中国至印尼、印度、柬 埔寨三国快递服务！
								</p>

								<p class="text-right status"><span>进行中</span></p>
								<p class="text-right grey">20xx.xx.xx-20xx.xx.xx</p>
								<p class="text-right grey">中国大陆适用</p>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="thumbnail">
							<img src="img/show/suyun/promotion4.png" alt="活动四">

							<div class="caption">
								<p>关于杭州举办“20国集团领导人第1x次 峰会”期间我司收派服务调整的通知...
								</p>

								<p class="text-right status"><span>进行中</span></p>
								<p class="text-right grey">20xx.xx.xx-20xx.xx.xx</p>
								<p class="text-right grey">中国大陆适用</p>
							</div>
						</div>
					</div>

				</div>

				<p class="text-center more">
					<a class="btn btn-danger" href="promotion.html" target="_blank">更多活动促销</a>
				</p>

			</div>

		</section>
		<!-- news area-->
		<section class="newsarea">
			<div class="col-md-6 col-md-offset-6 newslist">
				<div class="news">
					<h4>新闻公告 <span class="textmore">更多>></span></h4>
					<ul class="list-unstyled">
						<li class="list-group">关于20xx年杭州G20峰会期间收派服务调整的通知</li>
						<li class="list-group">请查阅国家邮政局、公安部、国家安全部《关于加强二十国集团（G20）峰会期间寄递物品安全管理通告》</li>
						<li class="list-group">速运快递开通加拿大、墨西哥流向服务</li>
						<li class="list-group">关于20xx年第4号台风“妮妲”登陆提醒</li>
						<li class="list-group">速运快递推出国际重货产品，开通广东、江苏、浙江、上海至美国重货快运服务</li>
						<li class="list-group">速运快递有限公司——（系统线上招标）20xx年8月公路运输项目采购招标公告</li>
						<li class="list-group">速运快递“领”鲜服务助力“蟹”闯天下</li>
						<li class="list-group">关于杭州举办“20国集团领导人第1x次峰会”期间我司收派服务调整的通知（滚动更新中）</li>

					</ul>
				</div>

			</div>

		</section>

		<section class="fresharea">
			<div class="container">
				<div class="areatitle">
					<h2 class="text-center"><span class="maintitle">企业特色</span></h2>
					<p class="text-center"><span class="subtitle">ENTERPRISE CHARACTERISTIC</span></p>
				</div>

				<div class="fresh">
					<div class="col-sm-6 col-md-6 left">
						<div class="media">
							<div class="media-left media-middle">
								<a href="#">
									<img class="media-object" src="img/show/suyun/tese1.png" alt="特色一">
								</a>
							</div>
							<div class="media-body">
								<h4 class="media-heading">协同运输，洞悉一切</h4>
								<p class="grey">从货主到收货人，无论网点间中转还是配送到门，创新的“协同运输”，让任意一个层级均能够管理订单并保持全程追踪。</p>
							</div>
						</div>

					</div>
					<div class="col-sm-6 col-md-6 right">
						<div class="media">
							<div class="media-left media-middle">
								<a href="#">
									<img class="media-object" src="img/show/suyun/tese2.png" alt="特色一">
								</a>
							</div>
							<div class="media-body">
								<h4 class="media-heading">亲密无间的伙伴网络</h4>
								<p class="grey">更注重运输执行过程中企业与企业之间，人与人之间胡亲密协作，还将帮助悄发现新的伙伴，简历更强大的协同运输网络。</p>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-6 left">
						<div class="media">
							<div class="media-left media-middle">
								<a href="#">
									<img class="media-object" src="img/show/suyun/tese3.png" alt="特色一">
								</a>
							</div>
							<div class="media-body">
								<h4 class="media-heading">持续领先的运输平台</h4>
								<p class="grey">我们致力于构建持续领先的平台，保持每两周一次的迭代更新。并在持续的客户实施过程中，汲取并持续导入管理经验。</p>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-6 right">
						<div class="media">
							<div class="media-left media-middle">
								<a href="#">
									<img class="media-object" src="img/show/suyun/tese4.png" alt="特色一">
								</a>
							</div>
							<div class="media-body">
								<h4 class="media-heading">开放平台</h4>
								<p class="grey">基于开放互联的思想，为ERP/WMS等第三方软件及车载硬件设备提供接入服务，融入客户的既有IT架构。</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<div class="nav-bottom"></div>
		
		<a href="#0" class="cd-top">Top</a>
</body>
</html>