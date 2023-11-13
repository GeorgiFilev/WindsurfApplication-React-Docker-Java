import React from 'react';

const ProjectSummary=({project})=>{
  console.log(project)
  return(
    <div className="card z-depth-0 project-summary">
    <div className="card-content grey-text text-darken-3">
      <span className="card-title"> {project.title}</span>
      <p>{project.content}</p>
      <p className="gray-text">3rd november,author: {project.author}</p>
    </div>
  </div>
  )
}
export default ProjectSummary;