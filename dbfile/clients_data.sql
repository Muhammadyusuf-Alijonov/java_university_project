-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2023 at 06:11 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_clients_list`
--

-- --------------------------------------------------------

--
-- Table structure for table `clients_data`
--

CREATE TABLE `clients_data` (
  `client_id` int(99) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `second_name` varchar(255) NOT NULL,
  `passport` varchar(10) NOT NULL,
  `email` varchar(255) NOT NULL,
  `reserved_room` int(10) NOT NULL,
  `reservation_period` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `clients_data`
--

INSERT INTO `clients_data` (`client_id`, `first_name`, `second_name`, `passport`, `email`, `reserved_room`, `reservation_period`) VALUES
(72, 'Said', 'Amangeldiev', 'Ad6752', 'mymail.com', 12, '5 days'),
(73, 'Temur', 'Akhmedov', 'Ad67521', 'mymail.com', 54, '7 days');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clients_data`
--
ALTER TABLE `clients_data`
  ADD PRIMARY KEY (`client_id`),
  ADD UNIQUE KEY `passport` (`passport`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clients_data`
--
ALTER TABLE `clients_data`
  MODIFY `client_id` int(99) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
