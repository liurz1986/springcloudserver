package com.kedacom.keda.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kedacom.category.model.Category;
import com.kedacom.keda.common.Result;
import com.kedacom.keda.common.ResultUtil;
import com.kedacom.keda.service.CategoryService;
import com.kedacom.keda.service.UserService;
import com.kedacom.user.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@PostMapping("/login")
	@HystrixCommand(fallbackMethod = "loginFallback")
	@ResponseBody
	public Result login(User user, Model model, HttpSession session) {
		User u = userService.login(user);
		if (u.getPassword().equals(user.getPassword())) {
			session.setAttribute("userId", u.getId());
			session.setAttribute("userName", u.getName());

			Category category = categoryService.getCategory(1L);
			// 楼层
			model.addAttribute("category", category);
			return ResultUtil.success();
		}
		return ResultUtil.error(2, "用户名或密码有误");
	}

	@PostMapping("/register")
	@HystrixCommand(fallbackMethod = "registerFallback")
	@ResponseBody
	public Result register(User user, Map<String, Object> model) {
		if (userService.register(user)) {
			Category category = categoryService.getCategory(1L);
			// 楼层
			model.put("category", category);
			return ResultUtil.success();
		} else {
			return ResultUtil.error(2, "注册失败");
		}
	}

	// 熔断处理方法：同@HystrixCommand所用的方法中参数一样
	public Result loginFallback(User user, Model model, HttpSession session) {

		return ResultUtil.error(2, "用户名或密码有误");
	}

	// 熔断处理方法：同@HystrixCommand所用的方法中参数一样
	public Result registerFallback(User user, Map<String, Object> model) {

		return ResultUtil.error(2, "注册失败");
	}
}

// // 创建线程安全的Map
// static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long,
// User>());
//
// @RequestMapping(value="/", method= RequestMethod.GET)
// public List<User> getUserList() {
// // 处理"/users/"的GET请求，用来获取用户列表
// // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
// List<User> r = new ArrayList<User>(users.values());
// return r;
// }
//
// @RequestMapping(value="/", method=RequestMethod.POST)
// public String postUser(@ModelAttribute @Valid User user,BindingResult
// bindingResult) {
// // 处理"/users/"的POST请求，用来创建User
// // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
// if(bindingResult.hasErrors()){
// System.out.println(bindingResult.getFieldError().getDefaultMessage());
// return "error";
// }else {
// users.put(user.getId(), user);
// return "success";
// }
// }
//
// @RequestMapping(value="/{id}", method=RequestMethod.GET)
// public User getUser(@PathVariable Long id) {//@PathVariable
// 是从一个URI模板里面来填充,@RequestParam 是从request里面取值
// // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
// // url中的id可通过@PathVariable绑定到函数的参数中
// return users.get(id);
// }
//
// @RequestMapping(value="/{id}", method=RequestMethod.PUT)
// public String putUser(@PathVariable Long id, @ModelAttribute User user) {
// // 处理"/users/{id}"的PUT请求，用来更新User信息
// User u = users.get(id);
// u.setName(user.getName());
// u.setAge(user.getAge());
// users.put(id, u);
// return "success";
// }
//
// @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
// public String deleteUser(@PathVariable Long id) {
// // 处理"/users/{id}"的DELETE请求，用来删除User
// users.remove(id);
// return "success";
// }
//
// @RequestMapping(value = "/testExce",method = RequestMethod.GET)
// public void testException() throws Exception {
// Result result = new Result();
//
// result.toString();
//// throw new MyException(100,"出现了异常");
// }
