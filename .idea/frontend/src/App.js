import React, { useState, useEffect } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AuthService from "./services/auth.service";

import Login from "./components/Login";
import Top200GlobalChart from "./components/Top200GlobalChart";
import Top200PolandChart from "./components/Top200PolandChart";
import Viral50GlobalChart from "./components/Viral50GlobalChart";
import Viral50PolandChart from "./components/Viral50PolandChart";
import Register from "./components/Register";
import Home from "./components/Home";
import Profile from "./components/Profile";

import EventBus from "./common/EventBus";

const App = () => {
  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if (user) {
      setCurrentUser(user);
    }

    EventBus.on("logout", () => {
      logOut();
    });

    return () => {
      EventBus.remove("logout");
    };
  }, []);

  const logOut = () => {
    AuthService.logout();
    setCurrentUser(undefined);
  };

  return (
    <div>
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <Link to={"/"} className="navbar-brand">
          SpotifyCharts
        </Link>
        <div className="navbar-nav mr-auto">
        {currentUser && (
          <li className="nav-item">
            <Link to={"/Viral50PolandChart"} className="nav-link">
              Viral50 Poland 
            </Link>
          </li>
        )}
          {currentUser && (
          <li className="nav-item">
            <Link to={"/Viral50GlobalChart"} className="nav-link">
              Viral50 Global
            </Link>
          </li>
          )}
          {currentUser && (
          <li className="nav-item">
            <Link to={"/Top200PolandChart"} className="nav-link">
              Top200 Poland
            </Link>
          </li>
          )}
          {currentUser && (
          <li className="nav-item">
            <Link to={"/Top200GlobalChart"} className="nav-link">
              Top200 Global
            </Link>
          </li>
          )}
          {currentUser && (
          <li className="nav-item">
            <a href="http://localhost:8081/db/spotify/top200" className="nav-link">
              Top200 Details
            </a>
          </li>
          )}
          {currentUser && (
          <li className="nav-item">
            <a href="http://localhost:8081/db/spotify/viral50" className="nav-link">
              Viral50 Details
            </a>
          </li>
          )}

        </div>

        {currentUser ? (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/profile"} className="nav-link">
                {currentUser.username}
              </Link>
            </li>
            <li className="nav-item">
              <a href="/login" className="nav-link" onClick={logOut}>
                Logout
              </a>
            </li>
          </div>
        ) : (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/login"} className="nav-link">
                Login
              </Link>
            </li>

            <li className="nav-item">
              <Link to={"/register"} className="nav-link">
                Sign Up
              </Link>
            </li>
          </div>
        )}
      </nav>

      <div className="container mt-3">
        <Routes>
          <Route path="/" element={<Home/>} />
          <Route path="/Top200PolandChart" element={<Top200PolandChart/>} />
          <Route path="/Top200GlobalChart" element={<Top200GlobalChart/>} />
          <Route path="/Viral50GlobalChart" element={<Viral50GlobalChart/>} />
          <Route path="/Viral50PolandChart" element={<Viral50PolandChart/>} />
          <Route path="/login" element={<Login/>} />
          <Route path="/register" element={<Register/>} />
          <Route path="/profile" element={<Profile/>} />
        </Routes>
      </div>

    </div>
  );
};

export default App;
