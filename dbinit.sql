DROP DATABASE IF EXISTS `blindwallsdb`;
CREATE DATABASE `blindwallsdb`;

-- aanmaken
CREATE USER 'demo_user'@'localhost' IDENTIFIED BY 'secret';

-- geef in een keer alle rechten - soort administrator!
GRANT ALL ON `blindwallsdb`.* TO 'demo_user'@'localhost';