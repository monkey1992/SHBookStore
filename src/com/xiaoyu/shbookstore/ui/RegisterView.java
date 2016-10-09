package com.xiaoyu.shbookstore.ui;
 
import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.xiaoyu.shbookstore.R;
import com.xiaoyu.shbookstore.config.ConstantValue;
import com.xiaoyu.shbookstore.config.GlobalParams;
import com.xiaoyu.shbookstore.domain.User;
import com.xiaoyu.shbookstore.engine.AccountEngine;
import com.xiaoyu.shbookstore.manager.PromptManager;
import com.xiaoyu.shbookstore.manager.UiManager;
import com.xiaoyu.shbookstore.util.BeanFactory;

public class RegisterView extends BaseView {
	
	private EditText etUsername;
	private EditText etPassword;
	private EditText etConfirmPwd;
	private TextView tvRegister;
	
	public RegisterView(Context context) {
		super(context);
	}

	@Override
	public void setListener() {
		tvRegister.setOnClickListener(this);
	}

	@Override
	public void init() {
		showInMiddle = (ViewGroup) View.inflate(context, R.layout.regist_activity, null);
		etUsername = (EditText) findViewById(R.id.regist_name_edit);
		etPassword = (EditText) findViewById(R.id.regist_pwd_edit);
		etConfirmPwd = (EditText) findViewById(R.id.regist_confirm_pwd_edit);
		tvRegister = (TextView) findViewById(R.id.regist_text);
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.regist_text:
			register();
			break;
		default:
			break;
		}
	}

	private void register() {
		String username = etUsername.getText().toString().trim();
		String password = etPassword.getText().toString().trim();
		String confirmPwd = etConfirmPwd.getText().toString().trim();
		if(!StringUtils.isNotBlank(username) || !StringUtils.isNotBlank(password)) {
			PromptManager.showToast(context, "用户名或密码不能为空");
			return;
		}
		if(!password.equals(confirmPwd)) {
			PromptManager.showToast(context, "两次密码不一样");
			return;
		}
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
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
				if(result == null ) {
					System.out.println("注册失败。。");
					PromptManager.showToast(context, GlobalParams.myError.getText());
				}else {
					PromptManager.showToast(context, result);
					UiManager.getUiManager().changeView(HomeView.class);
				}
			}

			@Override
			protected String doInBackground(User... params) {
				String result = null;
				if(ae.register(params[0]) ) {
					result = "注册成功";
				}
				return result;
			}
			
		}.executeProxy(user);
	}

	public int getIdentifier() {
		// TODO Auto-generated method stub
		return ConstantValue.VIEW_REGISTER;
	}
	
}
