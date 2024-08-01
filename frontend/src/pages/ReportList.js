import React from 'react';
import '../styles/ReportList.css';

const ReportList = ({ reports }) => {
  return (
    <div className="report-list">
      <h2>실종자 제보 리스트</h2>
      {reports.map((report) => (
        <div key={report.id} className="report-item">
          <h3>{report.name}</h3>
          <p>{report.report}</p>
          <p>연락처: {report.contact}</p>
        </div>
      ))}
    </div>
  );
};

export default ReportList;