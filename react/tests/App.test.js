import React from 'react';
import { render, fireEvent, screen } from '@testing-library/react';
import { BrowserRouter as Router } from 'react-router-dom';
import { Provider } from 'react-redux';
import { QueryClient, QueryClientProvider } from 'react-query';
import store from '../store';
import Login from '../Components/Login';
import '@testing-library/jest-dom/extend-expect';
import axios from 'axios';
import Signup from '../Components/Signup';
import ErrorPage from '../Components/ErrorPage';
import HomePage from '../Components/HomePage';
import AppliedSavingsPlan from '../ClientComponents/AppliedSavingsPlan';
import InquiryForm from '../ClientComponents/InquiryForm';
import ViewInquiry from '../FinancialConsultantComponents/ViewInquiry';
import ReplyForm from '../FinancialConsultantComponents/ReplyForm';
import ViewFeedbacks from '../RegionalManagerComponents/ViewFeedbacks';
import PlanApproval from '../RegionalManagerComponents/PlanApproval';
import PlanApplicationForm from '../ClientComponents/PlanApplicationForm';
import InquiryDetails from '../ClientComponents/InquiryDetails';
import CustomerPostFeedback from '../ClientComponents/CustomerPostFeedback';
import ViewAllSavingsPlan from '../ClientComponents/ViewAllSavingsPlan';
import SavingsPlanForm from '../FinancialConsultantComponents/SavingsPlanForm';
import ViewSavingsPlan from '../FinancialConsultantComponents/ViewSavingsPlan';
import PlanApplicationApproval from '../RegionalManagerComponents/PlanApplicationApproval';

jest.mock('axios');

// Setup QueryClient
const queryClient = new QueryClient();

describe('Login Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderLoginComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <Login {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  
  test('frontend_login_component_renders_the_with_login_heading', () => {
    renderLoginComponent();

  
    const loginHeadings = screen.getAllByText(/Login/i);
    expect(loginHeadings.length).toBeGreaterThan(0);

  });


  test('frontend_login_component_displays_validation_messages_when_login_button_is_clicked_with_empty_fields', () => {
    renderLoginComponent();

    fireEvent.click(screen.getByRole('button', { name: /Login/i }));

    expect(screen.getByText('Email is required')).toBeInTheDocument();
    expect(screen.getByText('Password is required')).toBeInTheDocument();
  });

   
});
describe('Signup Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderSignupComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <Signup {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };
  test('frontend_signup_component_renders_with_signup_heading', () => {
    renderSignupComponent();

    const signupHeadings = screen.getAllByText(/Register/i);
   expect(signupHeadings.length).toBeGreaterThan(0);

  });

  test('frontend_signup_component_displays_validation_messages_when_submit_button_is_clicked_with_empty_fields', () => {
    renderSignupComponent();

    fireEvent.click(screen.getByRole('button', { name: /Register/i }));

    expect(screen.getByText('User Name is required')).toBeInTheDocument();
    expect(screen.getByText('Email is required')).toBeInTheDocument();
    expect(screen.getByText('Mobile Number is required')).toBeInTheDocument();
    expect(screen.getByText('Password is required')).toBeInTheDocument();
    expect(screen.getByText('Confirm Password is required')).toBeInTheDocument();
  });

  test('frontend_signup_component_displays_error_when_passwords_do_not_match', () => {
    renderSignupComponent();

    fireEvent.change(screen.getByPlaceholderText('Password'), { target: { value: 'password123' } });
    fireEvent.change(screen.getByPlaceholderText('Confirm Password'), { target: { value: 'password456' } });
    fireEvent.click(screen.getByRole('button', { name: /Register/i }));

    expect(screen.getByText('Passwords do not match')).toBeInTheDocument();
  });
});

describe('ErrorPage Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });
  const renderErrorComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <ErrorPage {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };
  test('frontend_errorpage_component_renders_with_error_heading', () => {
    renderErrorComponent();
    const headingElement = screen.getByText(/Oops! Something Went Wrong/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_errorpage_component_renders_with_error_content', () => {
    renderErrorComponent();
    const paragraphElement = screen.getByText(/Please try again later./i);
    expect(paragraphElement).toBeInTheDocument();
  });
});
describe('Home Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });
  const renderHomeComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <HomePage {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  
  test('frontend_home_component_renders_with_heading', () => {
    renderHomeComponent();
    const headingElement = screen.getAllByText(/Welcome to FinanceHub/i);
    expect(headingElement.length).toBe(1);


  });
});

describe('ViewAllSavingsPlan Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderViewAllSavingsPlanComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <ViewAllSavingsPlan {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_ViewAllSavingsPlan_Client_component_renders_the_with_heading', () => {
    renderViewAllSavingsPlanComponent();

    const headingElement = screen.getByText(/Available Savings Plans/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_ViewAllSavingsPlan_Client_component_renders_the_table', () => {
    renderViewAllSavingsPlanComponent();

    const tableElement = screen.getByRole('table');
    expect(tableElement).toBeInTheDocument();
  });
});


describe('ViewSavingsPlan Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderViewSavingsPlanComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <ViewSavingsPlan {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_ViewSavingsPlan_FinancialConsultant_component_renders_the_with_heading', () => {
    renderViewSavingsPlanComponent();

    const headingElement = screen.getByText(/Savings Plans/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_ViewSavingsPlan_FinancialConsultant_component_renders_the_table', () => {
    renderViewSavingsPlanComponent();

    const tableElement = screen.getByRole('table');
    expect(tableElement).toBeInTheDocument();
  });
});

describe('PlanApplicationForm Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });


  const renderPlanApplicationFormComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <PlanApplicationForm {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_PlanApplicationForm_Client_component_renders_the_with_heading', () => {
    renderPlanApplicationFormComponent();

    const headingElement = screen.getByText(/Plan Application Form/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_PlanApplicationForm_Client_component_displays_required_validation_messages', async () => {
    renderPlanApplicationFormComponent();

    fireEvent.click(screen.getByRole('button', { name: /Submit/i }));

    expect(await screen.findByText('Applied amount is required')).toBeInTheDocument();
  });
});


describe('SavingsPlanForm Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });


  const renderSavingsPlanFormComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <SavingsPlanForm {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_SavingsPlanForm_FinancialConsultant_component_renders_the_with_heading', () => {
    renderSavingsPlanFormComponent();

    const headingElement = screen.getByText(/Create New Savings Plan/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_SavingsPlanForm_FinancialConsultant_component_displays_required_validation_messages', async () => {
    renderSavingsPlanFormComponent();

    fireEvent.click(screen.getByRole('button', { name: /Add Savings Plan/i }));

    expect(await screen.findByText('Plan Name is required')).toBeInTheDocument();
    expect(await screen.findByText('Goal Amount is required')).toBeInTheDocument();
    expect(await screen.findByText('Time Frame is required')).toBeInTheDocument();
    expect(await screen.findByText('Risk Level is required')).toBeInTheDocument();
    expect(await screen.findByText('Description is required')).toBeInTheDocument();
  });
});

describe('ClientPostFeedback Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });


  const renderCustomerPostFeedbackComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <CustomerPostFeedback {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_Clientpostfeedback_Client_component_renders_the_with_heading', () => {
    renderCustomerPostFeedbackComponent();

    const headingElement = screen.getByText(/Submit Your Feedback/i);
    expect(headingElement).toBeInTheDocument();
  });
});


describe('AppliedSavingsPlan Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });


  
  const renderAppliedSavingsPlanComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <AppliedSavingsPlan {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_AppliedSavingsPlan_Client_component_renders_the_with_heading', () => {
    renderAppliedSavingsPlanComponent();
    const headingElements = screen.getAllByText(/Applied Plans/i);
    expect(headingElements.length).toBe(1);


  });

test('frontend_AppliedSavingsPlan_Client_component_renders_the_table', () => {
  renderAppliedSavingsPlanComponent();

  const tableElement = screen.getByRole('table');
  expect(tableElement).toBeInTheDocument();
});
});


describe('InquiryDetails Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderInquiryDetailsComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <InquiryDetails {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_InquiryDetails_Client_component_renders_the_with_heading', () => {
    renderInquiryDetailsComponent();

    const headingElement = screen.getAllByText(/My Inquiries/i);
    expect(headingElement.length).toBe(1);

  });
});

describe('InquiryForm Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderInquiryFormComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <InquiryForm {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_InquiryForm_Client_component_renders_the_with_heading', () => {
    renderInquiryFormComponent();

    const headingElement = screen.getByText(/ubmit Your Inquiry/i);
    expect(headingElement).toBeInTheDocument();
  });
});

describe('ViewInquiry Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderViewInquiryComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <ViewInquiry {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_ViewInquiry_FinancialConsultant_component_renders_the_with_heading', () => {
    renderViewInquiryComponent();

    const headingElement = screen.getByText(/View Inquiries/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_ViewInquiry_FinancialConsultant_component_renders_the_table', () => {
    renderViewInquiryComponent();

    const tableElement = screen.getByRole('table');
    expect(tableElement).toBeInTheDocument();
  });
});

describe('ReplyForm Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderReplyFormComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <ReplyForm {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_ReplyForm_FinancialConsultant_component_renders_the_with_update_heading', () => {
    renderReplyFormComponent();

    const headingElement = screen.getByText(/Update Inquiry Status/i);
    expect(headingElement).toBeInTheDocument();
  });
});

describe('ViewFeedbacks Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });
  
  const renderViewFeedbacksComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <ViewFeedbacks {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_ViewFeedbacks_RegionalManager_component_renders_the_heading', () => {
    renderViewFeedbacksComponent();

    const headingElement = screen.getByText(/Customer Feedbacks/i);
    expect(headingElement).toBeInTheDocument();
  });
});

describe('PlanApproval Component', () => {
  const renderPlanApprovalComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <PlanApproval {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_PlanApproval_RegionalManager_component_renders_the_heading', () => {
    renderPlanApprovalComponent();
    
    const headingElement = screen.getAllByText(/Savings Plans/i);
    expect(headingElement.length).toBe(1);

  });

  test('frontend_PlanApproval_RegionalManager_component_renders_the_table', () => {
    renderPlanApprovalComponent();

    const tableElement = screen.getByRole('table');
    expect(tableElement).toBeInTheDocument();
  });


  describe('PlanApplicationApproval Component', () => {
    const renderPlanApplicationApprovalComponent = (props = {}) => {
      const queryClient = new QueryClient(); // Create a new QueryClient instance
      return render(
        <Provider store={store}>
          <QueryClientProvider client={queryClient}>
            <Router>
              <PlanApplicationApproval {...props} />
            </Router>
          </QueryClientProvider>
        </Provider>
      );
    };
  
    test('frontend_PlanApplicationApproval_RegionalManager_component_renders_the_heading', () => {
      renderPlanApplicationApprovalComponent();
      
      const headingElement = screen.getAllByText(/Savings Plan Applications/i);
      expect(headingElement.length).toBe(1);

    });
  
    test('frontend_PlanApplicationApproval_RegionalManager_component_renders_the_table', () => {
      renderPlanApplicationApprovalComponent();
  
      const tableElement = screen.getByRole('table');
      expect(tableElement).toBeInTheDocument();
    });
  });
});
