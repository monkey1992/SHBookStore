package com.xiaoyu.shbookstore.ui;

import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.config.GlobalParams;
import com.xiaoyu.shbookstore.domain.User;
import com.xiaoyu.shbookstore.engine.AccountEngine;
import com.xiaoyu.shbookstore.manager.PromptManager;
import com.xiaoyu.shbookstore.manager.UiManager;
import com.xiaoyu.shbookstore.util.BeanFactory;

public class LoginView extends BaseView {
	
	private EditText etUsername;
	private EditText etPassword;
	
	private TextView tvLogin;
	private TextView tvRegister;
	
	public LoginView(Context context) {
		super(context);
	}

	@Override
	public void setListener() {
		tvLogin.setOnClickListener(this);
		tvRegister.setOnClickListener(this);
	}

	@Override
	public void init() {
		showInMiddle = (ViewGroup) View.inflate(context, R.layout.login_activity, null);
		etUsername = (EditText) findViewById(R.id.login_name_edit);
		etPassword = (EditText) findViewById(R.id.login_pwd_edit);
		tvLogin = (TextView) findViewById(R.id.login_text);
		tvRegister = (TextView) findViewById(R.id.regist_text);
		
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_text:
			login();
			break;
		case R.id.regist_text:
			register();
			break;
		default:
			break;
		}
		
		super.onClick(v);
	}

	private void register() {
		UiManager.getUiManager().changeView(RegisterView.class);
	}

	private void login() {	
		String username = etUsername.getText().toString().trim();
		String password = etPassword.getText().toString().trim();
		if(!StringUtils.isNotBlank(username) || !StringUtils.isNotBlank(password)) {
			PromptManager.showToast(context, "用户名或密码不能为空");
			return;
		}
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);	
		//通过BeanFactory获得接口AccountEngine的实现类AccountEngineImpl
		//ae便是AccountEngineImpl对象
		final AccountEngine ae = BeanFactory.getImpl(AccountEngine.class);
		new MyHttpAsyncTask<User>(){		
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				PromptManager.showProgressDialog(context);
			}

			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				PromptManager.closeProgressDialog();
				if(result != null) {
					moveToViewById();
					PromptManager.showToast(context, result);
				}else {
					PromptManager.showToast(context, GlobalParams.myError.getText());
				}
			}

			@Override
			protected String doInBackground(User... params) {
				String result = null;
				if(ae.login(params[0]) ) {
					GlobalParams.isLogin = true;
					result = "登录成功";
				}
				return result;
			}
		}.executeProxy(user);
	}
	
	private void moveToViewById() {
		switch (GlobalParams.preViewId) {
		case ConstantValue.VIEW_REGISTER:
			UiManager.getUiManager().changeView(HomeView.class);
			break;
		case ConstantValue.VIEW_MORE:
			UiManager.getUiManager().changeView(MoreView.class);
			break;
		case ConstantValue.VIEW_SHOPPING_CART:
			UiManager.getUiManager().changeView(ShoppingCart.class);
			break;
		case ConstantValue.VIEW_PRODUCT_DETAIL:
			UiManager.getUiManager().changeView(ProductDetail.class);
			break;
		default:
			UiManager.getUiManager().changeView(HomeView.class);
			break;
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override
	public void onResume() {	
		super.onResume();
	}
	
	public int getIdentifier() {
		return ConstantValue.VIEW_LOGIN;
	}	
}
