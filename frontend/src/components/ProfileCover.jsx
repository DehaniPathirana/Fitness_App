import React from "react";
import pageImg from '../assets/images/page-img/dum1.jpg'
import profileImg from '../assets/images/user/11.png'
import '../css/Profile.css'



export default function ProfileCover() {
    return (
        <div>
            <div className="container">
                <div className="row">
                    <div className="col-sm-12">
                        <div className="profile-card card">
                            <div className="card-body profile-page p-0">
                                <div className="profile-header">
                                    <div className="profile-position position-relative">
                                        <img
                                            src={pageImg}
                                            alt="profile-bg"
                                            className="pro-img rounded img-fluid"
                                        />
                                        <ul className="header-nav list-inline d-flex flex-wrap justify-end p-0 m-0">
                                            <li>
                                                <a href="#">
                                                    <i className="ri-pencil-line"/>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i className="ri-settings-4-line"/>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div className="user-detail text-center mb-3">
                                        <div className="profile-img">
                                            <img
                                                src={profileImg}
                                                alt="profile-img"
                                                className="ava avatar-130 img-fluid"
                                            />
                                        </div>
                                        <div className="profile-detail">
                                            <h3 className="">Viduth Ranaweera</h3>
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
