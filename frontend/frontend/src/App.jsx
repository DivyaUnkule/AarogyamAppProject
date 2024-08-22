import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import AuthPage from './Pages/AuthPage';
import SignInPage from './Pages/SignInPage';
import Admin from './Pages/Admin';

import WaterIntakePage from './Pages/WaterIntakePage';
import ArticlePage from './Pages/ArticlePage';
import RegularUserNavbar from './NavBar/RegularUserNavBar';
import UserProfile from './Pages/UserProfile';
import ViewHealthInfo from './Pages/ViewHealthInfo';
import UpdateHealthInfo from './Pages/UpdateHealthInfo';
import InsertHealthInfo from './Pages/InsertHealthInfo';
import WeightLossUserNavbar from './NavBar/WeightLossUserNavBar';
import RegularMealPlanPage from './Pages/RegularMealPlanPage';
import WeightLossMealPlanPage from './Pages/WeightLossMealPlan';
import WeightGainMealPlanPage from './Pages/WeightGainMealPlan';
import GetExerciseYoga from './Pages/GetExerciseYoga';
import CreateExerciseYoga from './Pages/CreateExerciseYoga';
import ExerciseYogaDetails from './Pages/ExerciseYogaDetails';
import UpdateExerciseYoga from './Pages/UpdateExerciseYoga';
import DeleteExerciseYoga from './Pages/DeleteExerciseYoga';

import UserHealthInfoList from './Pages/UserHealthInfoList';
import GetUserHealthInfoById from './Pages/GetUserHealthInfoById';

import CreateWaterIntake from './Pages/CreateWaterIntake';
import WaterIntakeUpdate from './Pages/WaterIntakeUpdate';
import DeleteWaterIntake from './Pages/DeleteWaterIntake';
import WaterIntakeList from './Pages/WaterIntakeList';
import GetWaterIntakeById from './Pages/GetWaterIntakeById';

import UserRegistrationPage from './Pages/UserRegistrationPage';
import UserListPage from './Pages/UserListPage';
import UserDetailsPage from './Pages/UserDetailsPage';
import UpdateUserPage from './Pages/UpdateUserPage';
import DeleteUserPage from './Pages/DeleteUserPage';

import ProfilePicUpload from './Pages/ProfilePicUpload';
import UserProfileImage from './Pages/UserProfileImage';
import InsertMealPlan from './Pages/InsertMealPlan';
import GetAllMealPlans from './Pages/GetAllMealPlan';
import UpdateMealPlan from './Pages/UpdateMealPlan';
import DeleteMealPlan from './Pages/DeleteMealPlan';
import InsertArticle from './Pages/InsertArticle';
import UpdateArticle from './Pages/UpdateArticle';
import ViewAllArticles from './Pages/ViewAllArticles';
import DeleteArticle from './Pages/DeleteArticle';
import WeightGainUserNavbar from './NavBar/WeightGainUserNavBar';
import RegularExerciseYogaPage from './Pages/RegularExerciseYogaPage';
import WeightLossExerciseYogaPage from './Pages/WeightLossUserExercise';
import WeightGainExerciseYogaPage from './Pages/WeightGainUserExercise';
import UserProgressPage from './Pages/UserProgressPage';
import AdminAllUserProgressPage from './Pages/AdminAllUserProgressPage';
import ForgotPasswordPage from './Pages/ForgetPasswordPage';
import PaymentPage from './Pages/PaymentPage';
import AdminPaymentsPage from './Pages/AdminPaymentsPage';
import ProfileImage from './Pages/ProfileImage';

import LandingPage from './Pages/LandingPage';

function App() {
  const userId = localStorage.getItem('userId');
  return (
    
    <Router>
      <Routes>
      <Route path="/" element={<LandingPage/>} />
        <Route path="/signin" element={<SignInPage />} />
        <Route path="/register" element={<AuthPage />} />
        <Route path="/forgotpassword" element={<ForgotPasswordPage />} />
        <Route path="/users/admin" element={<Admin />} />
        <Route path="/regularuser/home" element={<RegularUserNavbar/>} />
        <Route path="/weightloss/home" element={<WeightLossUserNavbar/>} />
        <Route path="/weightgain/home" element={<WeightGainUserNavbar/>} />
        <Route path="/progress" element={<UserProgressPage/>} />
        <Route path="/allprogress" element={<AdminAllUserProgressPage/>} />
        <Route path="/payment" element={<PaymentPage/>} />
        <Route path="/adminpayment" element={<AdminPaymentsPage/>} />
        <Route path="/exercises" element={<RegularExerciseYogaPage />}/>
        <Route path="/weightlossexercises" element={<WeightLossExerciseYogaPage />}/>
        <Route path="/weightgainexercises" element={<WeightGainExerciseYogaPage/>}/>
        <Route path="/mealplan" element={<RegularMealPlanPage/>}/>
        <Route path="/weightlossmealplan" element={<WeightLossMealPlanPage/>}/>
        <Route path="/weightgainmealplan" element={<WeightGainMealPlanPage/>}/>
        <Route path="/waterintake" element={<WaterIntakePage />}/>
        <Route path="/articles" element={<ArticlePage/>}/>
        <Route path="/userprofile" element={<UserProfile userId={userId}/>}/>
        <Route path="/healthinfo/view" element={<ViewHealthInfo userId={userId}/>}/>
        <Route path="/healthinfo/update" element={<UpdateHealthInfo userId={userId}/>}/>
        <Route path="/healthinfo/insert" element={<InsertHealthInfo userId={userId}/>}/>
         <Route path="/user/{userId}/image_upload" element={<ProfilePicUpload/>} />
         <Route path="/user/{userId}/image" element={<ProfileImage userId={userId}/>} />

        {/* Admin */}

        {/* Exercise Yoga */}
        <Route path="/admin/getexercises" element={<GetExerciseYoga />} />
        <Route path="/admin/insertexercise" element={<CreateExerciseYoga />} />
        <Route path="/admin/getexercisebyId" element={<ExerciseYogaDetails />} />
        <Route path="/admin/updateexercise" element={<UpdateExerciseYoga />} />
        <Route path="/admin/deleteexercise" element={<DeleteExerciseYoga />} />

        {/*UserHealthInfo*/}
        <Route path="/admin/getuserhealthinfos" element={<UserHealthInfoList />} />
        <Route path="/admin/getUserById" element={<GetUserHealthInfoById />} />

        {/*Water Intake*/}
        <Route path="/admin/insertwaterintake" element={<CreateWaterIntake />} />
        <Route path="/admin/updatewaterintake" element={<WaterIntakeUpdate />} />
        <Route path="/admin/deletewaterintake" element={<DeleteWaterIntake />} />
        <Route path="/admin/getwaterintakes" element={<WaterIntakeList />} />
        <Route path="/admin/getwaterintakebyid" element={<GetWaterIntakeById />} />

        {/*User*/}
        <Route path="/admin/addUser" element={<UserRegistrationPage />} />
        <Route path="/admin/getAllUsers" element={<UserListPage />} />
        <Route path="/admin/getUserById" element={<UserDetailsPage />} />
        <Route path="/admin/updateUserDetails" element={<UpdateUserPage />} />
        <Route path="/admin/deleteUserById" element={<DeleteUserPage />} />
        <Route path="/user/{userId}/image_upload" element={<ProfilePicUpload />} />
        <Route path="/user/{userId}/image" element={<ProfileImage userId={userId} />} />

         {/* MealPlan */}
        <Route path="/admin/insertmealplan" element={<InsertMealPlan />}/>
        <Route path="/admin/getallmealplan" element={<GetAllMealPlans />} />
        <Route path="/admin/updatemealplan" element={<UpdateMealPlan />} />
        <Route path="/admin/deletemealplan" element={<DeleteMealPlan />} /> 
     


      {/*Articles*/}
      <Route path="/admin/insertarticle" element={<InsertArticle />} />
        <Route path="/admin/getallarticles" element={<ViewAllArticles />} />
        <Route path="/admin/updatearticle" element={<UpdateArticle />} />
        <Route path="/admin/deletearticle" element={<DeleteArticle />} />
      </Routes>
    </Router>
  );
}

export default App;
