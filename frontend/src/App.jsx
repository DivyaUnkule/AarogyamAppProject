import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import AuthPage from './Pages/AuthPage';
import SignInPage from './Pages/SignInPage';
import Admin from './Pages/Admin';




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





function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<SignInPage />} />
        <Route path="/register" element={<AuthPage />} />
        <Route path="/users/admin" element={<Admin />} />
        

       {/* Exercise Yoga */}
        <Route path="/admin/getexercises" element={<GetExerciseYoga />} />
        <Route path="/admin/insertexercise" element={<CreateExerciseYoga />} />
        <Route path="/admin/getexercisebyId" element={<ExerciseYogaDetails />} />
        <Route path="/admin/updateexercise" element={<UpdateExerciseYoga />} />
        <Route path="/admin/deleteexercise" element={<DeleteExerciseYoga />} />

        {/*UserHealthInfo*/}
        <Route path="/admin/getuserhealthinfos" element={<UserHealthInfoList />} />
        <Route path="/admin/getuserhealthinfobyId" element={<GetUserHealthInfoById />} />

        {/*Water Intake*/}
        <Route path="/admin/insertwaterintake" element={<CreateWaterIntake />} />
        <Route path="/admin/updatewaterintake" element={<WaterIntakeUpdate />} />
        <Route path="/admin/deletewaterintake" element={<DeleteWaterIntake />} />
        <Route path="/admin/getwaterintakes" element={<WaterIntakeList />} />

       
        

        
        
      </Routes>
    </Router>
  );
}

export default App;
