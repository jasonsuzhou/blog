<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>博客后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<div class="header">

		<div class="dl-title">
			<!--<img src="/chinapost/Public/assets/img/top.png">-->
		</div>

		<div class="dl-log">
			Welcome，<span class="dl-log-user">${sesAdminUser }</span><a
				href="admin/adminlogout.do" title="Logout"
				class="dl-log-quit">[Sign Out]</a>
		</div>
	</div>
	<div class="content">
		<div class="dl-main-nav">
			<div class="dl-inform">
				<div class="dl-inform-title">
					<s class="dl-inform-icon dl-up"></s>
				</div>
			</div>
			<ul id="J_Nav" class="nav-list ks-clear">
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-home">System Management</div></li>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">User Profile</div></li>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">Auditor</div></li>

			</ul>
		</div>
		<ul id="J_NavContent" class="dl-tab-conten">

		</ul>
	</div>
	<script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="assets/js/bui-min.js"></script>
	<script type="text/javascript" src="assets/js/common/main-min.js"></script>
	<script type="text/javascript" src="assets/js/config-min.js"></script>
	<script>
		BUI.use('common/main', function() {
			var config = [ {
				id : '1',
				menu : [ {
					text : 'System Management',
					items : [ {
						id : '1',
						text : 'Update Footer',
						href : 'admin/adminConfigFooter.do'
					},{
						id : '2',
						text : 'My Profile',
						href : 'admin/gotoEditProfilePage.do'
					}]
				},{
					text : 'Content Management',
					items : [{
						id : '2',
						text : 'Query Messages',
						href : 'admin/gotoMessagePage.do'
					},{
						id : '3',
						text : 'Query Moments',
						href : 'admin/gotoMomentsPage.do'
					},{
						id : '4',
						text : 'Blog Categories',
						href : 'admin/gotoBlogCategoryPage.do'
					},{
						id : '5',
						text : 'Blog Articles',
						href : 'admin/gotoBlogArticlePage.do'
					},{
						id : '6',
						text : 'Query Links',
						href : 'admin/gotoLinkPage.do'
					}]
				} ]
			}, {
				id : '2',
				homePage : '1',
				menu : [ {
					text : 'User Profile',
					items : [ {
						id : '1',
						text : 'My Profile',
						href : 'admin/gotoEditProfilePage.do'
					},{
						id : '2',
						text : 'Change Password',
						href : 'admin/gotoAdminChangePassPage.do'
					}]
				}]
			},{
				id : '3',
				homePage : '1',
				menu : [ {
					text : 'Auditor',
					items : [ {
						id : '1',
						text : 'Query Audtior',
						href : 'admin/gotoAdminAuditorPage.do'
					} ]
				} ]
			} ];
			new PageUtil.MainPage({
				modulesConfig : config
			});
		});
	</script>
</body>
</html>