import React, { useState, useRef } from "react";
import { FaUpload } from "react-icons/fa";
import SidebarLayout from "../components/SidebarLayout";

export default function CreatePost() {
  const [files, setFiles] = useState([null, null, null]);
  const fileInputs = useRef([]);

  const handleFileChange = (event, index) => {
    const uploadedFile = event.target.files[0];
    const newFiles = [...files];
    newFiles[index] = uploadedFile;
    setFiles(newFiles);
    // Reset the value of the file input to prevent the file selection window from appearing again
    event.target.value = "";
  };

  const handleCardClick = (index) => {
    fileInputs.current[index].click();
  };

  const validateFile = (file) => {
    if (file.type.includes("image")) {
      return true; // Allow image files
    } else if (file.type.includes("video")) {
      // Check video duration
      if (file.duration <= 30) {
        return true; // Allow video files with duration <= 30 seconds
      } else {
        alert("Video length should not exceed 30 seconds.");
        return false; // Do not allow video files with duration > 30 seconds
      }
    } else {
      alert("Unsupported file type. Please upload an image or a video.");
      return false; // Do not allow other file types
    }
  };

  const renderCard = (index) => {
    const file = files[index];

    if (file) {
      return (
        <div
          className="card"
          style={{ height: "330px", width: "300px", marginLeft: index > 0 ? "30px" : "0" }}
          onClick={() => handleCardClick(index)}
        >
          {file.type.includes("image") ? (
            <img src={URL.createObjectURL(file)} alt="Uploaded File" style={{ width: "100%", height: "100%" }} />
          ) : (
            <video src={URL.createObjectURL(file)} style={{ width: "100%", height: "100%" }} controls />
          )}
        </div>
      );
    } else {
      return (
        <div
          className="card"
          style={{ height: "330px", width: "300px", marginLeft: index > 0 ? "30px" : "0", cursor: "pointer", position: "relative", display: "flex", justifyContent: "center", alignItems: "center" }}
          onClick={() => handleCardClick(index)}
        >
          <FaUpload size={48} />
          <input
            type="file"
            ref={(input) => (fileInputs.current[index] = input)}
            accept="image/*, video/*" // Accept both image and video files
            style={{ display: "none" }}
            onChange={(event) => {
              const uploadedFile = event.target.files[0];
              if (validateFile(uploadedFile)) {
                handleFileChange(event, index);
              }
            }}
          />
        </div>
      );
    }
  };

  return (
    <div className="App">
      <SidebarLayout />

      <div
        className="content p-5 "
        style={{ marginTop: "60px", marginLeft: "350px" }}
      >
        <div className=" mb-5" style={{marginRight:"800px" , marginTop:"-10px"}}>
          <h1>Create Post</h1>
        </div>
        <div className="card" style={{ height: "660px", width: "900px"}}>
          <div className="d-flex p-5">
            {Array.from({ length: 3 }).map((_, index) => (
              <React.Fragment key={index}>
                {renderCard(index)}
              </React.Fragment>
            ))}
          </div>

          <div className="">
            <div className="mb-2" style={{marginRight:"690px"}}>
            <label className="fs-5">Description :</label>
            </div>

            <textarea style={{width:"800px",height:"100px"}}/>

          </div>

          <div className="d-flex align-items-start mt-4" style={{marginLeft:"50px"}} >
            <button className="btn btn-primary">Post</button>
          </div>
        </div>
      </div>
    </div>
  );
}
