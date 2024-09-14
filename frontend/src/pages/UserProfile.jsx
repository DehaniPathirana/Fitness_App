import React from 'react'
import ProfileCover from "../components/ProfileCover";
import SidebarLayout from "../components/SidebarLayout";
import ProfileFeed from "../components/ProfileFeed";

export default function UserProfile() {
  return (
    <div>
      <div className="App">
          <SidebarLayout/>
        <ProfileCover/>
          <ProfileFeed/>
      </div>

    </div>
  )
}
