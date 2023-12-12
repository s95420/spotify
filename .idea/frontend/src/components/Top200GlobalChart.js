import React, { useEffect, useState } from "react";
import Pagination from "@material-ui/lab/Pagination";
import { Chart } from "react-chartjs-2";
import {
  Chart as ChartJS,
  BarController,
  BarElement,
  PointElement,
  LinearScale,
  Title,
  Tooltip,
  TimeScale,
} from "chart.js";
import { CategoryScale } from "chart.js";
import AuthService from "../services/auth.service";
import { toXML } from "jstoxml";
ChartJS.register(
  PointElement,
  LinearScale,
  Title,
  Tooltip,
  TimeScale,
  BarController,
  BarElement,
  CategoryScale
);
const currentUser = AuthService.getCurrentUser();
export const options = {
  plugins: {
    title: {
      display: true,
      text: "Popularność artystów w kategorii Top200 - cały świat",
      font: {
        size: 15,
        weight: "bold",
      },
    },
  },
  scales: {
    x: {
      ticks: {
        maxRotation: 90,
        minRotation: 60,
        autoSkip: false,
        font: {
          size: 10,
        },
      },
      title: {
        display: true,
        text: "Nazwa artysty/ów i tytuł utworu",
        font: {
          size: 15,
          weight: "bold",
        },
      },
    },
    y: {
      title: {
        display: true,
        text: "Ilość odtworzeń",
        font: {
          size: 15,
          weight: "bold",
        },
      },
    },
  },
};

export const Top200GlobalChart = () => {
  const [content, setContent] = useState([]);
  const [page, setPage] = useState(1);
  const [count, setCount] = useState(0);
  const xLabels = [];
  const yLabels = [];
  const apiURL = `http://localhost:8080/api/v1/top200/Global?page=${page}&sortDate=DESC&sortRank=ASC`;

  useEffect(() => {
    (async () => {
      const response = await fetch(apiURL, {
        method: "GET",
        headers: {
          Authorization: `Bearer ${currentUser.token}`,
        },
      });
      const json = await response.json();
      setCount(json.totalPages);
      setContent(json.content);
    })();
  }, [apiURL]);

  const fetchDataChart = async () => {
    const response = await fetch(apiURL, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${currentUser.token}`,
      },
    });
    //console.log(currentUser.token)
    const json = await response.json();
    setCount(json.totalPages);
    setContent(json.content);
  };

  content &&
    content.forEach((v) => {
      xLabels.push(v.artist + " - " + v.title);
      yLabels.push(v.streams);
    });

  const handlePageChange = (event, value) => {
    setPage(value);
    fetchDataChart();
  };
  const JsonFile = () => {
    const element = document.createElement("a");
    const saveFile = JSON.stringify(content);
    const textFile = new Blob([saveFile], { type: "application/json" }); //pass data from localStorage API to blob
    element.href = URL.createObjectURL(textFile);
    element.download = "file.json";
    document.body.appendChild(element);
    element.click();
  };

  const XmlFile = () => {
    const xmlOptions = {
      header: true,
      indent: "  ",
    };
    const element = document.createElement("a");
    const saveFile = toXML(
      {
        _name: "top200Global",
        _attrs: {
          version: "1.0",
        },
        _content: {
          records: [content],
        },
      },
      xmlOptions
    );
    const textFile = new Blob([saveFile], { type: "application/xml" }); //pass data from localStorage API to blob
    element.href = URL.createObjectURL(textFile);
    element.download = "file.xml";
    document.body.appendChild(element);
    element.click();
  };
  return (
    <div>
      <div className="container bg-light">
        <Chart
          height={700}
          width={1500}
          options={options}
          type="bar"
          data={{
            labels: xLabels,
            datasets: [
              {
                label: "Ilość odtworzeń:",
                backgroundColor: [
                  "#3e95cd",
                  "#8e5ea2",
                  "#3cba9f",
                  "#e8c3b9",
                  "#c45850",
                  "#CD5C5C",
                  "#40E0D0",
                ],
                data: yLabels,
              },
            ],
          }}
        />
      </div>
      <Pagination
        className="my-3 d-flex justify-content-center"
        count={count}
        page={page}
        siblingCount={1}
        boundaryCount={1}
        variant="outlined"
        shape="rounded"
        onChange={handlePageChange}
      />
      <div className="mt-5 col-md-12 text-center">
        <button className="btn btn-primary mr-2" onClick={JsonFile}>
          Zapisz do pliku JSON
        </button>
        <button className="btn btn-primary" onClick={XmlFile}>
          Zapisz do pliku XML
        </button>
      </div>
    </div>
  );
};
export default Top200GlobalChart;
