-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 29 mars 2019 à 20:58
-- Version du serveur :  10.1.38-MariaDB
-- Version de PHP :  7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `charity`
--

-- --------------------------------------------------------

--
-- Structure de la table `animal`
--

CREATE TABLE `animal` (
  `idamin` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `etat` varchar(2500) NOT NULL,
  `declaree` tinyint(1) NOT NULL,
  `montant` double NOT NULL,
  `iduser` int(11) NOT NULL,
  `photo` varchar(255) NOT NULL,
  `video` varchar(255) NOT NULL,
  `date_creation` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idcat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `idcat` int(11) NOT NULL,
  `idsoucat` int(11) DEFAULT NULL,
  `nomcat` varchar(255) NOT NULL,
  `description` varchar(2500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`idcat`, `idsoucat`, `nomcat`, `description`) VALUES
(2, 1, 'catedit', 'desc cat2'),
(3, 2, 'cat1', 'desc cat1'),
(4, 1, 'cat1', 'desc cat1');

-- --------------------------------------------------------

--
-- Structure de la table `contribution`
--

CREATE TABLE `contribution` (
  `idcon` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `idpost` int(11) NOT NULL,
  `description` varchar(2500) NOT NULL,
  `montant` double NOT NULL,
  `date_con` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

CREATE TABLE `event` (
  `idevent` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `idcat` int(11) NOT NULL,
  `photo` varchar(255) NOT NULL,
  `date_event` datetime NOT NULL,
  `description` varchar(2500) NOT NULL,
  `lat` double NOT NULL,
  `lon` double NOT NULL,
  `lieu` varchar(255) NOT NULL,
  `video` varchar(255) NOT NULL,
  `titre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `membre`
--

CREATE TABLE `membre` (
  `idM` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `nom1` varchar(255) NOT NULL,
  `nom2` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `date_creation` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(2500) DEFAULT NULL,
  `photo` varchar(2500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `membre`
--

INSERT INTO `membre` (`idM`, `username`, `email`, `password`, `enable`, `nom1`, `nom2`, `role`, `date_creation`, `description`, `photo`) VALUES
(1, 'shsh', 'hs@email.com', 'aaaaaaa', 0, 'ssj', 'slalk', 'admin', '2019-03-29 09:14:20', 'description', 'photo'),
(4, 'aaaa', 'hs@email.com', 'aaaaaaa', 0, 'ssj', 'slalk', 'admin', '2019-03-29 09:14:20', 'descripssssssssssstion', 'photo'),
(5, 'bbbbbbbb', 'hs@email.com', 'aaaaaaa', 0, 'ssj', 'slalk', 'admin', '2019-03-29 09:14:20', 'description', 'photo');

-- --------------------------------------------------------

--
-- Structure de la table `participer`
--

CREATE TABLE `participer` (
  `idevent` int(11) NOT NULL,
  `iduser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `payment`
--

CREATE TABLE `payment` (
  `idpay` int(11) NOT NULL,
  `idroom` int(11) NOT NULL,
  `idpost` int(11) NOT NULL,
  `montant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `post`
--

CREATE TABLE `post` (
  `idpost` int(11) NOT NULL,
  `idcat` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `description` varchar(2500) NOT NULL,
  `image` varchar(255) NOT NULL,
  `video` varchar(255) NOT NULL,
  `lieu` varchar(255) NOT NULL,
  `jour_val` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `montant` double NOT NULL,
  `date_creation` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `rating`
--

CREATE TABLE `rating` (
  `idevent` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `idrec` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `idobjet` int(11) NOT NULL,
  `description` varchar(2500) DEFAULT NULL,
  `raison` varchar(255) NOT NULL,
  `typeobjet` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `room`
--

CREATE TABLE `room` (
  `idroom` int(11) NOT NULL,
  `roomname` varchar(255) NOT NULL,
  `montant` double NOT NULL,
  `categorie` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `animal`
--
ALTER TABLE `animal`
  ADD PRIMARY KEY (`idamin`),
  ADD KEY `iduser` (`iduser`),
  ADD KEY `idcat` (`idcat`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`idcat`),
  ADD KEY `idsoucat` (`idsoucat`);

--
-- Index pour la table `contribution`
--
ALTER TABLE `contribution`
  ADD PRIMARY KEY (`idcon`),
  ADD KEY `description` (`description`(767)),
  ADD KEY `iduser` (`iduser`),
  ADD KEY `idpost` (`idpost`);

--
-- Index pour la table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`idevent`),
  ADD KEY `iduser` (`iduser`),
  ADD KEY `idcat` (`idcat`);

--
-- Index pour la table `membre`
--
ALTER TABLE `membre`
  ADD PRIMARY KEY (`idM`);

--
-- Index pour la table `participer`
--
ALTER TABLE `participer`
  ADD KEY `idevent` (`idevent`),
  ADD KEY `iduser` (`iduser`);

--
-- Index pour la table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`idpay`),
  ADD KEY `idpost` (`idpost`),
  ADD KEY `idroom` (`idroom`);

--
-- Index pour la table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`idpost`),
  ADD KEY `idcat` (`idcat`),
  ADD KEY `iduser` (`iduser`);

--
-- Index pour la table `rating`
--
ALTER TABLE `rating`
  ADD KEY `idevent` (`idevent`),
  ADD KEY `iduser` (`iduser`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`idrec`),
  ADD KEY `iduser` (`iduser`),
  ADD KEY `idpost` (`idobjet`);

--
-- Index pour la table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`idroom`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `animal`
--
ALTER TABLE `animal`
  MODIFY `idamin` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `idcat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `contribution`
--
ALTER TABLE `contribution`
  MODIFY `idcon` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `event`
--
ALTER TABLE `event`
  MODIFY `idevent` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `membre`
--
ALTER TABLE `membre`
  MODIFY `idM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `payment`
--
ALTER TABLE `payment`
  MODIFY `idpay` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `idrec` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `room`
--
ALTER TABLE `room`
  MODIFY `idroom` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `animal`
--
ALTER TABLE `animal`
  ADD CONSTRAINT `animal_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `membre` (`idM`),
  ADD CONSTRAINT `animal_ibfk_2` FOREIGN KEY (`idcat`) REFERENCES `categorie` (`idcat`);

--
-- Contraintes pour la table `contribution`
--
ALTER TABLE `contribution`
  ADD CONSTRAINT `contribution_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `membre` (`idM`),
  ADD CONSTRAINT `contribution_ibfk_2` FOREIGN KEY (`idpost`) REFERENCES `post` (`idpost`);

--
-- Contraintes pour la table `participer`
--
ALTER TABLE `participer`
  ADD CONSTRAINT `participer_ibfk_1` FOREIGN KEY (`idevent`) REFERENCES `event` (`idevent`),
  ADD CONSTRAINT `participer_ibfk_2` FOREIGN KEY (`iduser`) REFERENCES `membre` (`idM`);

--
-- Contraintes pour la table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`idroom`) REFERENCES `room` (`idroom`);

--
-- Contraintes pour la table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`idcat`) REFERENCES `categorie` (`idcat`),
  ADD CONSTRAINT `post_ibfk_2` FOREIGN KEY (`iduser`) REFERENCES `membre` (`idM`);

--
-- Contraintes pour la table `rating`
--
ALTER TABLE `rating`
  ADD CONSTRAINT `rating_ibfk_1` FOREIGN KEY (`idevent`) REFERENCES `event` (`idevent`),
  ADD CONSTRAINT `rating_ibfk_2` FOREIGN KEY (`iduser`) REFERENCES `membre` (`idM`);

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `reclamation_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `membre` (`idM`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
