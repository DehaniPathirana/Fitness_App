// import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router , Route , Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from './pages/Login';
import Register from './pages/Register';
import LandingPage from './pages/LandingPage';
import CreatePost from './pages/CreatePost';
import CreateMealPlan from "./pages/CreateMealPlan";
import UserProfile from "./pages/UserProfile";
// import Test from "./pages/Test";
import Mealfeed from "./pages/Mealfeed";
// import MealUpdate from "./pages/MealUpdate";
import Authenticate from "./pages/Authenticate";
import MealUpdate from "./pages/MealUpdate";


function App() {
  return (
    <div className="App">

      <Router>
        <Routes>
          <Route exact path='/' element={<Register/>}/>
          <Route exact path='/login' element={<Login/>}/>
          <Route exact path='/home' element={<LandingPage/>}/>
          <Route exact path='/create-post' element={<CreatePost/>}/>
          <Route exact path='/create-meal' element={<CreateMealPlan/>}/>
          <Route exact path='/post-feed' element={<CreateMealPlan/>}/>
          <Route exact path='/profile' element={<UserProfile/>}/>
          <Route exact path='/meal-feed' element={<Mealfeed/>}/>
          <Route exact path='/meal-update' element={<MealUpdate/>}/>
          <Route exact path='/authenticate' element={<Authenticate/>}/>


        </Routes>
      </Router>
      
    </div>
  );
}

export default App;
