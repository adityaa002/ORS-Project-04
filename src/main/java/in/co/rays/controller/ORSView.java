package in.co.rays.controller;

public interface ORSView {

	public String APP_CONTEXT = "/Project-04";
	public String PAGE_FOLDER = "/jsp";

	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";

	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String USER_CTL = APP_CONTEXT + "/UserCtl";
}
