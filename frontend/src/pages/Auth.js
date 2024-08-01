import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Auth.css'; // CSS 파일을 사용하여 스타일링

// 샘플 데이터
const initialReports = [
  { id: 1, name: 'John Doe', date: '2024-08-01', location: 'Park', resolved: false },
  { id: 2, name: 'Jane Smith', date: '2024-08-02', location: 'Grocery Store', resolved: true },
  { id: 3, name: 'Alice Johnson', date: '2024-08-03', location: 'Library', resolved: false },
];

function Auth() {
  const navigate = useNavigate();
  const [reports, setReports] = useState(initialReports);

  const handleCheckboxChange = (id) => {
    setReports(reports.map((report) =>
      report.id === id ? { ...report, resolved: !report.resolved } : report
    ));
  };

  const handleCellClick = (id) => {
    navigate(`/report/${id}`);
  };
    
  const handleSave = () => {
    // 저장 처리 로직을 여기에 추가할 수 있습니다.
    alert('저장되었습니다.');
  };

  return (
    <div className="auth-container">
      <header className="auth-header">
        <h1>MPS(실종자 제보 서비스)</h1>
        <table className="user-info-table">
          <tbody>
            <tr>
              <td><strong>소속:</strong> 경찰청</td>
              <td><strong>사번:</strong> 123456</td>
            </tr>
            <tr>
              <td><strong>직위:</strong> 담당자</td>
              <td><strong>이름:</strong> 김철수</td>
            </tr>
          </tbody>
        </table>
      </header>

      <h2>실종자 제보 관리</h2>
      <h3>▶실종자 제보 리스트</h3>
      <table className="reports-table">
        <thead>
          <tr>
            <th>순번</th>
            <th>실종자 이름</th>
            <th>발생일시</th>
            <th>발생장소</th>
            <th>해결여부</th>
          </tr>
        </thead>
        <tbody>
          {reports.map((report) => (
            <tr key={report.id}>
              <td onClick={() => handleCellClick(report.id)}>{report.id}</td>
              <td onClick={() => handleCellClick(report.id)}>{report.name}</td>
              <td onClick={() => handleCellClick(report.id)}>{report.date}</td>
              <td onClick={() => handleCellClick(report.id)}>{report.location}</td>
              <td>
                <input 
                  type="checkbox" 
                  checked={report.resolved} 
                  onChange={() => handleCheckboxChange(report.id)} 
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <button className="save-button" onClick={handleSave}>저장</button>
    </div>
  );
}

export default Auth;