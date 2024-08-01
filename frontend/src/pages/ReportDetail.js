import React from 'react';
import '../styles/ReportDetail.css'; // CSS 파일을 사용하여 스타일링

// 샘플 데이터
const sampleReport = {
  name: 'John Doe',
  date: '2024-08-01',
  location: 'Park',
  guardianName: 'Jane Doe',
  guardianContact: '010-9876-5432',
  reports: [
    {
      reporterName: 'Jane Smith',
      reporterContact: '010-1234-5678',
      textInfo: 'Last seen at the park near the lake.',
      photo: 'photo1.jpg',
      locationInfo: '37.7749° N, 122.4194° W',
      phoneReportCompleted: true,
      status: '진행중',
    },
    {
      reporterName: 'Alice Johnson',
      reporterContact: '010-5678-1234',
      textInfo: 'Saw near the library entrance.',
      photo: 'photo2.jpg',
      locationInfo: '37.7749° N, 122.4194° W',
      phoneReportCompleted: false,
      status: '해결완료',
    },
  ],
};

function ReportDetail() {
  const { name, date, location, guardianName, guardianContact, reports } = sampleReport;

  return (
    <div className="report-detail-container">
      <section className="basic-info">
        <h2>기본정보</h2>
        <p><strong>실종자 이름:</strong> {name}</p>
        <p><strong>실종 발생일자:</strong> {date}</p>
        <p><strong>실종 발생지역:</strong> {location}</p>
        <p><strong>보호자 이름:</strong> {guardianName}</p>
        <p><strong>보호자 연락처:</strong> {guardianContact}</p>
      </section>

      <section className="report-info">
        <h2>제보내역</h2>
        {reports.length > 0 ? (
          <table className="report-info-table">
            <thead>
              <tr>
                <th>순번</th>
                <th>진행사항</th>
                <th>제보자 이름</th>
                <th>제보자 연락처</th>
                <th>텍스트 정보</th>
                <th>사진 파일 정보</th>
                <th>위치 정보</th>
                <th>전화 제보 완료</th>
              </tr>
            </thead>
            <tbody>
              {reports.map((report, index) => (
                <tr key={index} className={report.status === '해결완료' ? 'resolved' : ''}>
                  <td>{index + 1}</td>
                  <td>{report.status}</td>
                  <td>{report.reporterName}</td>
                  <td>{report.reporterContact}</td>
                  <td>{report.textInfo}</td>
                  <td>{report.photo}</td>
                  <td>{report.locationInfo}</td>
                  <td>{report.phoneReportCompleted ? '완료' : '미완료'}</td>
                </tr>
              ))}
            </tbody>
          </table>
        ) : (
          <p>제보내역: 없음</p>
        )}
      </section>
    </div>
  );
}

export default ReportDetail;