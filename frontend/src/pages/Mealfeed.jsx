import React from "react";
import "../css/SideBar.css";
import SidebarLayout from "../components/SidebarLayout";
import { RiUserFollowLine } from "react-icons/ri";
import {Link} from "react-router-dom";
import MealComponent from "../components/MealComponent";
import LoadGif from "../assets/images/page-load-loader.gif"

export default function Mealfeed()
{

    return (
        <div className="App">
            <SidebarLayout />


            <Link to="/create-meal">
                <button  type="button" className="btn btn-primary" style={{width:'200px',marginLeft:'1200px',marginTop:'100px'}}>Add Meals</button>
            </Link>
            <Link to="/meal-update">
                <button  type="button" className="btn btn-primary" style={{width:'200px',marginLeft:'1200px',marginTop:'10px'}}>Update Meal</button>
            </Link>
            <div
                className="content"
                style={{ marginTop: "00px", marginLeft: "300px" }}
            >
                <div className="mt-4" style={{}}>
                    <div className="d-flex ">
                        <div className="">
                            <div
                                class="shadow p-3 mb-5 rounded"
                                style={{
                                    width: "840px",
                                    height: "auto",
                                    backgroundColor: "#e6e6e6",
                                }}
                            >

                                <MealComponent/>


                            </div>
                            <div className="col-sm-12 text-center">
                                <img src={LoadGif} alt="LoadGif" style={{height:'100px'}}/>
                            </div>
                        </div>

                        <div>
                            <div
                                className="shadow p-3 mb-5  rounded"
                                style={{
                                    width: "300px",
                                    height: "600px",
                                    marginLeft: "45px",
                                    backgroundColor: "#e6e6e6",
                                }}
                            >
                                <h2>Who to Follow</h2>

                                <div
                                    className="shadow p-3 mb-2 bg-white rounded d-flex mt-4"
                                    style={{ width: "auto" }}
                                >
                                    <div>
                                        <img
                                            src="./images/profile-icon.png"
                                            style={{
                                                width: "40px",
                                                height: "40px",
                                                borderRadius: "10px",
                                                marginRight: "20px", // Adjust margin for proper spacing
                                            }}
                                            alt="User Profile"
                                        />
                                    </div>

                                    <div className="mt-2">
                                        <p>Viduth</p>
                                    </div>

                                    <div>
                                        <button
                                            type="button"
                                            className="btn btn-primary d-flex align-items-center"
                                            style={{ marginLeft: "50px" }}
                                        >
                                            <RiUserFollowLine
                                                style={{ marginRight: "5px", fontSize: "5px" }}
                                            />
                                            Follow
                                        </button>{" "}
                                    </div>
                                </div>

                                <div
                                    className="shadow p-3 mb-2 bg-white rounded d-flex mt-4"
                                    style={{ width: "auto" }}
                                >
                                    <div>
                                        <img
                                            src="./images/profile-icon.png"
                                            style={{
                                                width: "40px",
                                                height: "40px",
                                                borderRadius: "10px",
                                                marginRight: "20px", // Adjust margin for proper spacing
                                            }}
                                            alt="User Profile"
                                        />
                                    </div>

                                    <div className="mt-2">
                                        <p>Dehani</p>
                                    </div>

                                    <div>
                                        <button
                                            type="button"
                                            className="btn btn-primary d-flex align-items-center"
                                            style={{ marginLeft: "50px" }}
                                        >
                                            <RiUserFollowLine
                                                style={{ marginRight: "5px", fontSize: "5px" }}
                                            />
                                            Follow
                                        </button>{" "}
                                    </div>
                                </div>

                                <div
                                    className="shadow p-3 mb-2 bg-white rounded d-flex mt-4"
                                    style={{ width: "auto" }}
                                >
                                    <div>
                                        <img
                                            src="./images/profile-icon.png"
                                            style={{
                                                width: "40px",
                                                height: "40px",
                                                borderRadius: "10px",
                                                marginRight: "20px", // Adjust margin for proper spacing
                                            }}
                                            alt="User Profile"
                                        />
                                    </div>

                                    <div className="mt-2">
                                        <p>Himesh</p>
                                    </div>

                                    <div>
                                        <button
                                            type="button"
                                            className="btn btn-primary d-flex align-items-center"
                                            style={{ marginLeft: "50px" }}
                                        >
                                            <RiUserFollowLine
                                                style={{ marginRight: "5px", fontSize: "5px" }}
                                            />
                                            Follow
                                        </button>{" "}
                                    </div>
                                </div>

                                <div
                                    className="shadow p-3 mb-2 bg-white rounded d-flex mt-4"
                                    style={{ width: "auto" }}
                                >
                                    <div>
                                        <img
                                            src="./images/profile-icon.png"
                                            style={{
                                                width: "40px",
                                                height: "40px",
                                                borderRadius: "10px",
                                                marginRight: "20px", // Adjust margin for proper spacing
                                            }}
                                            alt="User Profile"
                                        />
                                    </div>

                                    <div className="mt-2">
                                        <p>Viduth</p>
                                    </div>

                                    <div>
                                        <button
                                            type="button"
                                            className="btn btn-primary d-flex align-items-center"
                                            style={{ marginLeft: "50px" }}
                                        >
                                            <RiUserFollowLine
                                                style={{ marginRight: "5px", fontSize: "5px" }}
                                            />
                                            Follow
                                        </button>{" "}
                                    </div>
                                </div>

                                <button
                                    className="btn btn-primary mt-2"
                                    style={{ width: "270px" }}
                                >
                                    See More ....
                                </button>

                            </div>

                            <div
                                class="shadow p-3 mb-5  rounded"
                                style={{
                                    width: "300px",
                                    height: "500px",
                                    marginLeft: "45px",
                                    backgroundColor: "#e6e6e6",
                                }}
                            ></div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
