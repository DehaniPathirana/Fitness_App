import React, {useEffect, useState} from 'react';
import ProfileTimeline from "./ProfileTimeline";
import axios from "axios";

const UserProfile = () => {
    const [activeTab, setActiveTab] = useState('timeline');

    const handleTabClick = (tabId) => {
        setActiveTab(tabId);
    };

    const [user, setUser] = useState([]);

    useEffect(() => {
        // Retrieve userId from local storage
        const userId = localStorage.getItem('userId');

        // Make GET request to fetch user details
        axios.get(`http://localhost:8082/users/${userId}`)
            .then(response => {
                // Handle successful response
                setUser(response.data);
                console.log(user.email)
            })
            .catch(error => {
                // Handle error
                console.error('Error fetching user details:', error);
            });
    }, []);

    return (
        <div className="wrapper">
            <div id="content-page" className="content-page">
                <div className="container">
                    <div className="timeline row">
                        <div className="col-sm-12">
                            <div className="card">
                                <div className="card-body p-0">
                                    <div className="pro-tab user-tabing">
                                        <ul className="prof-tab nav nav-pills d-flex align-items-center justify-content-center profile-feed-items p-0 m-0">
                                            <li className="nav-item col-12 col-sm-3 p-0">
                                                <a
                                                    className={`timelin-link nav-link ${activeTab === 'timeline' ? 'active' : ''}`}
                                                    href="#pills-timeline-tab"
                                                    onClick={() => handleTabClick('timeline')}
                                                    role="button"
                                                >
                                                    Timeline
                                                </a>
                                            </li>
                                            <li className="nav-item col-12 col-sm-3 p-0">
                                                <a
                                                    className={`nav-link ${activeTab === 'about' ? 'active' : ''}`}
                                                    href="#pills-about-tab"
                                                    onClick={() => handleTabClick('about')}
                                                    role="button"
                                                >
                                                    About
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="card" style={{padding:'20px',width:'98%',marginLeft:'1%'}}>
                        <div className="col-sm-12">
                            <div className="tab-content">
                                <div
                                    className={`tab-pane fade ${activeTab === 'about' ? 'show active' : ''}`}
                                    id="about"
                                    role="tabpanel"
                                ><h4>Contact Information</h4>
                                    <hr/>
                                    <div className="row">
                                        <div className="col-3">
                                            <h5>Name</h5>
                                        </div>
                                        <div className="col-9">
                                            <p className="mb-0">{user.firstName + ' ' + user.lastName}</p>

                                        </div>
                                        <div className="col-3">
                                            <h5>Email</h5>
                                        </div>
                                        <div className="col-9">
                                            <p className="mb-0">{user.email}</p>
                                        </div>
                                        <div className="col-3">
                                            <h5>Mobile</h5>
                                        </div>
                                        <div className="col-9">
                                            <p className="mb-0">{user.address}</p>
                                        </div>
                                        <div className="col-3">
                                            <h5>Address</h5>
                                        </div>
                                        <div className="col-9">
                                            <p className="mb-0">{user.address}</p>
                                        </div>
                                    </div>
                                    <hr/>
                                    <h4 className="mt-3">Basic Information</h4>
                                    <hr/>
                                    <div className="row">
                                        <div className="col-3">
                                            <h5>Birth Date</h5>
                                        </div>
                                        <div className="col-9">
                                            <p className="mb-0">{user.dob}</p>
                                        </div>
                                        <div className="col-3">
                                            <h5>interested in</h5>
                                        </div>
                                        <div className="col-9">
                                            <p className="mb-0">{user.interest}</p>
                                        </div>
                                        <div className="col-3">
                                            <h5>language</h5>
                                        </div>
                                        <div className="col-9">
                                            <p className="mb-0">{user.language}</p>
                                        </div>
                                    </div>
                                </div>
                                <div
                                    className={`tab-pane fade ${activeTab === 'timeline' ? 'show active' : ''}`}
                                    id="timeline"
                                    role="tabpanel"
                                >

                                    <ProfileTimeline/>

                                    <div className="col-sm-12 text-center">
                                        <img
                                            src="../assets/images/page-img/page-load-loader.gif"
                                            alt="loader"
                                            style={{height: 100}}
                                        />
                                    </div>

                                </div>
                            </div>
                        </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    );
}

export default UserProfile;
