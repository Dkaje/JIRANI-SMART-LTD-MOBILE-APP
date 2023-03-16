-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 27, 2022 at 07:25 AM
-- Server version: 10.5.16-MariaDB
-- PHP Version: 7.3.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id19297569_jiranismart`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reg_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`, `reg_date`) VALUES
('JIRANI~SMT-0372022', 'Admin', '25d55ad283aa400af464c76d713c07ad', '2022-07-20 09:14:38');

-- --------------------------------------------------------

--
-- Table structure for table `agent`
--

CREATE TABLE `agent` (
  `entry` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `id_no` int(11) DEFAULT NULL,
  `fname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `location` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT 0,
  `comment` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reg_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `agent`
--

INSERT INTO `agent` (`entry`, `id_no`, `fname`, `lname`, `email`, `phone`, `country`, `location`, `password`, `status`, `comment`, `reg_date`) VALUES
('JIRANI~SMT-041~2022', 37578456, 'Diana', 'Ndinda', 'ndindadiana@gmail.com', '0764325497', 'Nyamira', NULL, '25d55ad283aa400af464c76d713c07ad', 1, '', '2022-07-20 09:26:56');

-- --------------------------------------------------------

--
-- Table structure for table `application`
--

CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `borrow_id` int(11) DEFAULT NULL,
  `loan_id` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_no` int(11) DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `loan` float DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `mpesa` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fina_status` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `reg_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `application`
--

INSERT INTO `application` (`id`, `borrow_id`, `loan_id`, `id_no`, `name`, `phone`, `loan`, `amount`, `status`, `mpesa`, `fina_status`, `reg_date`) VALUES
(1, 1, 'JL-012', 64948454, 'Moraa Moraa', '0764664849', 20000, 20600, 'Disbursed', 'JAJSHSH344', 'Defaulter', '2022-07-20 09:36:04'),
(2, 2, 'JL-012', 64346484, 'Peter Waweru', '0787546464', 20000, 20600, 'Disbursed', 'JAHAGDG363', 'Pending', '2022-07-20 11:53:58');

-- --------------------------------------------------------

--
-- Table structure for table `banker`
--

CREATE TABLE `banker` (
  `id` int(11) NOT NULL,
  `amount` float DEFAULT NULL,
  `disbursed` float DEFAULT 0,
  `interest` float DEFAULT 0,
  `balance` float DEFAULT NULL,
  `reg_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `banker`
--

INSERT INTO `banker` (`id`, `amount`, `disbursed`, `interest`, `balance`, `reg_date`) VALUES
(1, 1000000, 40000, 100, 960100, '2022-07-20 09:29:44');

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `entry` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `id_no` int(11) DEFAULT NULL,
  `account` bigint(20) DEFAULT NULL,
  `fname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `agent_email` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `location` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT 0,
  `comment` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reg_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`entry`, `id_no`, `account`, `fname`, `lname`, `email`, `phone`, `country`, `agent_email`, `location`, `password`, `status`, `comment`, `reg_date`) VALUES
('JIRANI~SMT-042~2022', 64948454, 6464346498876, 'Moraa', 'Moraa', 'moraamoraa@gmail.com', '0764664849', 'Nyamira', 'ndindadiana@gmail.com', 'Keroka, Kamwene', '25d55ad283aa400af464c76d713c07ad', 1, '', '2022-07-20 09:28:24'),
('JIRANI~SMT-043~2022', 87546464, 9798184546467, 'Adele', 'adele', 'adeleadele@gmail.com', '0794646655', 'Garissa', 'ndindadiana@gmail.com', 'Madar, kaloleni', '25d55ad283aa400af464c76d713c07ad', 1, '', '2022-07-20 10:05:39'),
('JIRANI~SMT-044~2022', 64346484, 9794545636978, 'Peter', 'Waweru', 'wawerupeter@gmail.com', '0787546464', 'Meru', 'ndindadiana@gmail.com', 'Kemu, Scholars', '25d55ad283aa400af464c76d713c07ad', 1, '', '2022-07-20 11:45:15');

-- --------------------------------------------------------

--
-- Table structure for table `collateral`
--

CREATE TABLE `collateral` (
  `reg_id` int(11) NOT NULL,
  `id_no` bigint(20) DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `county` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `location` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `agent_email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category_one` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type_one` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `existence_one` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `worth_one` float DEFAULT NULL,
  `image_one` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `one_sta` varchar(20) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `category_two` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type_two` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `existence_two` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `worth_two` float DEFAULT NULL,
  `image_two` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `two_sta` varchar(20) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `category_three` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type_three` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `existence_three` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `worth_three` float DEFAULT NULL,
  `image_three` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `three_sta` varchar(20) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `status` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `status_auc` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `reg_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `collateral`
--

INSERT INTO `collateral` (`reg_id`, `id_no`, `name`, `phone`, `email`, `county`, `location`, `agent_email`, `category_one`, `type_one`, `existence_one`, `worth_one`, `image_one`, `one_sta`, `category_two`, `type_two`, `existence_two`, `worth_two`, `image_two`, `two_sta`, `category_three`, `type_three`, `existence_three`, `worth_three`, `image_three`, `three_sta`, `status`, `status_auc`, `reg_date`) VALUES
(1, 64948454, 'Moraa Moraa', '0764664849', 'moraamoraa@gmail.com', 'Nyamira', 'Keroka, Kamwene', 'ndindadiana@gmail.com', 'Marketable Securities', 'Shares', '2', 35000, 'IMG2074495911.jpg', 'Seized', 'No Asset', '', '', 0, '', 'Pending', 'No Asset', '', '', 0, '', 'Pending', 'Assessed', 'Approved', '2022-07-20 09:32:11'),
(2, 87546464, 'Adele adele', '0794646655', 'adeleadele@gmail.com', 'Garissa', 'Madar, kaloleni', 'ndindadiana@gmail.com', 'Marketable Securities', 'Shares', '2', 40000, 'IMG778462265.jpg', 'Pending', 'No Asset', '', '', 0, '', 'Pending', 'No Asset', '', '', 0, '', 'Pending', 'Assessed', 'Approved', '2022-07-20 10:07:09'),
(3, 64346484, 'Peter Waweru', '0787546464', 'wawerupeter@gmail.com', 'Meru', 'Kemu, Scholars', 'ndindadiana@gmail.com', 'Marketable Securities', 'Shares', '2', 35000, 'IMG640951030.jpg', 'Pending', 'No Asset', '', '', 0, '', 'Pending', 'No Asset', '', '', 0, '', 'Pending', 'Assessed', 'Approved', '2022-07-20 11:46:55');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `message` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sender` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `send_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `reply` varchar(250) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `reply_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `loan`
--

CREATE TABLE `loan` (
  `id` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `loan_type` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `r_period` float DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `reg_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `loan`
--

INSERT INTO `loan` (`id`, `loan_type`, `r_period`, `rate`, `amount`, `reg_date`) VALUES
('JL-012', 'Personal Loan', 12, 3, 20000, '2022-07-20 09:29:59'),
('JL-013', 'Development Loan', 24, 6, 50000, '2022-07-20 09:30:13'),
('JL-015', 'Small Business Loan', 36, 10, 70000, '2022-07-20 09:31:02'),
('JL-016', 'Credit Card Loan', 48, 14, 100000, '2022-07-20 09:31:13');

-- --------------------------------------------------------

--
-- Table structure for table `loaner`
--

CREATE TABLE `loaner` (
  `reg_id` int(11) NOT NULL,
  `borrow_id` int(11) DEFAULT NULL,
  `mpesa` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_no` int(11) DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `interest` float DEFAULT NULL,
  `period` float DEFAULT NULL,
  `expected` float DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `current_period` int(11) DEFAULT NULL,
  `total` float DEFAULT NULL,
  `status` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `remarks` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `reg_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `loaner`
--

INSERT INTO `loaner` (`reg_id`, `borrow_id`, `mpesa`, `id_no`, `name`, `phone`, `interest`, `period`, `expected`, `amount`, `current_period`, `total`, `status`, `remarks`, `reg_date`) VALUES
(1, 1, 'JSJSKDHH23', 64948454, 'Moraa Moraa', '0764664849', 600, 12, 1717, 1717, 1, 1717, 'Approved', NULL, '2022-07-20 09:38:01'),
(2, 2, 'BSHSJSJN22', 64346484, 'Peter Waweru', '0787546464', 600, 12, 1717, 1717, 1, 1717, 'Approved', NULL, '2022-07-20 11:58:59');

-- --------------------------------------------------------

--
-- Table structure for table `loan_borrow`
--

CREATE TABLE `loan_borrow` (
  `borrow_id` int(11) NOT NULL,
  `loan_id` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `loan_type` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rate` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_no` int(11) DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `maxloan` float DEFAULT NULL,
  `loan` float DEFAULT NULL,
  `interest` float DEFAULT NULL,
  `loan_period` float DEFAULT NULL,
  `period` float DEFAULT 0,
  `total` float DEFAULT NULL,
  `expected_monthly` float DEFAULT NULL,
  `balance` float DEFAULT 0,
  `status` varchar(20) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `reg_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `pay_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `disburse` varchar(20) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `payment_status` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `fina_status` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `loan_borrow`
--

INSERT INTO `loan_borrow` (`borrow_id`, `loan_id`, `loan_type`, `rate`, `id_no`, `name`, `phone`, `maxloan`, `loan`, `interest`, `loan_period`, `period`, `total`, `expected_monthly`, `balance`, `status`, `reg_date`, `pay_date`, `disburse`, `payment_status`, `fina_status`) VALUES
(1, 'JL-012', 'Personal Loan', '3', 64948454, 'Moraa Moraa', '0764664849', 29750, 20000, 600, 12, 11, 20600, 1717, 18883, 'Approved', '2022-07-20 09:35:29', '2022-07-20 09:35:29', 'Disbursed', 'Adjusted', 'Defaulter'),
(2, 'JL-012', 'Personal Loan', '3', 64346484, 'Peter Waweru', '0787546464', 29750, 20000, 600, 12, 11, 20600, 1717, 18883, 'Approved', '2022-07-20 11:52:07', '2022-07-20 11:52:07', 'Disbursed', 'Pending', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `entry` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `fname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT 0,
  `comment` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reg_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`entry`, `fname`, `lname`, `email`, `phone`, `role`, `password`, `status`, `comment`, `reg_date`) VALUES
('JIRANI~SMT-038~2022', 'Shem', 'Aduda', 'adudashem@gmail.com', '0764345894', 'Auction', '25d55ad283aa400af464c76d713c07ad', 1, '', '2022-07-20 09:23:10'),
('JIRANI~SMT-039~2022', 'Ronald', 'Karani', 'karanironald@gmail.com', '0796434548', 'Inventory', '25d55ad283aa400af464c76d713c07ad', 1, '', '2022-07-20 09:24:45'),
('JIRANI~SMT-040~2022', 'Wariara', 'Gicharu', 'gicharuwariara@gmail.com', '0768032681', 'Finance', '25d55ad283aa400af464c76d713c07ad', 1, '', '2022-07-20 09:26:16');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `agent`
--
ALTER TABLE `agent`
  ADD PRIMARY KEY (`entry`);

--
-- Indexes for table `application`
--
ALTER TABLE `application`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `banker`
--
ALTER TABLE `banker`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`entry`);

--
-- Indexes for table `collateral`
--
ALTER TABLE `collateral`
  ADD PRIMARY KEY (`reg_id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `loan`
--
ALTER TABLE `loan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `loaner`
--
ALTER TABLE `loaner`
  ADD PRIMARY KEY (`reg_id`);

--
-- Indexes for table `loan_borrow`
--
ALTER TABLE `loan_borrow`
  ADD PRIMARY KEY (`borrow_id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`entry`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `application`
--
ALTER TABLE `application`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `banker`
--
ALTER TABLE `banker`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `collateral`
--
ALTER TABLE `collateral`
  MODIFY `reg_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `loaner`
--
ALTER TABLE `loaner`
  MODIFY `reg_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `loan_borrow`
--
ALTER TABLE `loan_borrow`
  MODIFY `borrow_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
