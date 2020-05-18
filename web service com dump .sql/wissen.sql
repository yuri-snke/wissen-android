-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 05-Dez-2017 às 17:02
-- Versão do servidor: 10.1.13-MariaDB
-- PHP Version: 5.5.37

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wissen`
--
CREATE DATABASE IF NOT EXISTS `wissen` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `wissen`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_denuneventos`
--

CREATE TABLE `tbl_denuneventos` (
  `Id_Denun` int(11) NOT NULL,
  `Email_User` varchar(75) NOT NULL,
  `Id_Evento` int(11) NOT NULL,
  `Motivo` varchar(150) NOT NULL,
  `Descricao` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_eventos`
--

CREATE TABLE `tbl_eventos` (
  `id` int(11) NOT NULL,
  `Nome` varchar(150) NOT NULL,
  `DataInicio` varchar(10) NOT NULL,
  `DataTermino` varchar(10) NOT NULL,
  `HoraInicio` time NOT NULL,
  `HoraTermino` time NOT NULL,
  `Responsavel` varchar(150) NOT NULL,
  `Preco` varchar(8) NOT NULL DEFAULT '0.00',
  `Descricao` mediumtext NOT NULL,
  `Imagens` varchar(350) NOT NULL DEFAULT 'SemImagem',
  `ImagemCapa` varchar(50) NOT NULL DEFAULT 'SemImagemCapa',
  `CEP` varchar(9) NOT NULL,
  `Estado` varchar(200) NOT NULL,
  `Cidade` varchar(200) NOT NULL,
  `Bairro` varchar(200) NOT NULL,
  `Local` varchar(200) NOT NULL,
  `Endereco` varchar(200) NOT NULL,
  `TelFixo` varchar(15) NOT NULL,
  `TelMovel` varchar(15) NOT NULL,
  `EmailCriador` varchar(150) NOT NULL,
  `Tipo` varchar(150) NOT NULL,
  `CpfCnpj` varchar(18) NOT NULL,
  `Privado` int(11) NOT NULL DEFAULT '0',
  `Confianca` int(11) NOT NULL DEFAULT '0',
  `Premium` int(11) NOT NULL DEFAULT '0',
  `Interessados` int(11) NOT NULL DEFAULT '0',
  `Comparecerao` int(11) NOT NULL DEFAULT '0',
  `Compareceram` int(11) NOT NULL DEFAULT '0',
  `Lat` varchar(100) DEFAULT NULL,
  `Longi` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tbl_eventos`
--

INSERT INTO `tbl_eventos` (`id`, `Nome`, `DataInicio`, `DataTermino`, `HoraInicio`, `HoraTermino`, `Responsavel`, `Preco`, `Descricao`, `Imagens`, `ImagemCapa`, `CEP`, `Estado`, `Cidade`, `Bairro`, `Local`, `Endereco`, `TelFixo`, `TelMovel`, `EmailCriador`, `Tipo`, `CpfCnpj`, `Privado`, `Confianca`, `Premium`, `Interessados`, `Comparecerao`, `Compareceram`, `Lat`, `Longi`) VALUES
(59, 'Rent - O musical ', '2017-12-01', '2017-12-10', '20:00:00', '22:00:00', 'Beatriz Campos ', '80.00', 'O espetáculo retrata um ano na vida de oito jovens que lidam com amores, problemas financeiros, questões de sexualidade e a finitude da vida. A história começa na véspera de Natal no apartamento onde moram Mark, um cineasta perdido que tenta a sorte na cidade grande, e Roger, um ex-integrante de uma famosa banda de punk rock que perdeu sua inspiração ao se ver frente a frente com a morte. Nos EUA, a peça venceu quatro prêmios Tony, considerado o Oscar dos musicais.', 'YYNGSUP4IN6MKT1FUDKTQ0XBD3Q3UCZPYRS.jpg;SAWZM2DTQ3MT68YUWOSXRX5C1IYAI1SKBAN.jpg', '0MLWK4I1G1QQPDLM5X2P3DCVMZ6IHL0VLEK.jpg', '04854-966', 'Sao Paulo', 'Sao Paulo', 'Jardins', 'Teatro FAAP', 'Rua Alagoas, 903', '(11)5568-9855', '(11)96587-8856', 'yurioliveira@gmail.com', 'Exposicao;Festival;Outros', '460.284.908-30', 0, 0, 0, 101, 98, 0, NULL, NULL),
(60, 'Cantando na chuva - O musical ', '2017-10-31', '2017-12-05', '20:00:00', '22:00:00', 'Victor Silva', '260.00', 'Os icônicos papeis interpretados por Gene Kelly e Jean Hagen agora ganham vida por interpretações de Claudia Raia e Jarbas Homem de Mello. A clássica história se passa nos idos de 1920 em plena Hollywood. Os atores Don Lockwood e Lina Lamont são as estrelas da época, o casal preferido da indústria cinematográfica. Sucesso entre o público, os dois são os queridinhos da mídia, que aposta num relacionamento mais íntimo entres eles, algo, entretanto, que jamais existiu. ', 'YYNGSUP4IN6MKT1FUDKTQ0XBD3Q3UCZPYRS.jpg;SAWZM2DTQ3MT68YUWOSXRX5C1IYAI1SKBAN.jpg', '0MLWK4I1G1QQPDLM5X2P3DCVMZ6IHL0VLEK.jpg', '03265-444', 'São Paulo ', 'São Paulo ', 'Centro ', 'Teatro Santander', 'Avenida Presidente Juscelino Kubitschek, 2041', '(11)5541-9966', '(11)96587-5551', 'yurioliveira@gmail.com', 'Exposicao;Festival;Outros', '460.284.908-30', 0, 0, 0, 123, 89, 0, NULL, NULL),
(61, 'Vamp - O musical ', '2017-10-15', '2017-11-29', '17:00:00', '20:00:00', 'Natan Vieira', '150.00', 'Como na novela, exibida em 1991, a trama conta a história de Natasha, uma cantora que vende a alma para Conde Vlad em troca do sucesso na carreira. Ele, apaixonado por sua presa, faz de tudo para conquistá-la, mas com o passar do tempo Natasha só tenta se livrar dele e da maldição de ser vampira para sempre. ', 'YYNGSUP4IN6MKT1FUDKTQ0XBD3Q3UCZPYRS.jpg;SAWZM2DTQ3MT68YUWOSXRX5C1IYAI1SKBAN.jpg', '0MLWK4I1G1QQPDLM5X2P3DCVMZ6IHL0VLEK.jpg', '02145-999', 'Sao Paulo', 'Sao Paulo', 'Centro', 'Teatro Sérgio Cardoso', 'Rua Rui Barbosa, 153', '(11)5565-9855', '(11)96658-8744', 'yurioliveira@gmail.com', 'Exposicao;Festival;Outros', '460.284.908-30', 0, 0, 0, 103, 89, 0, NULL, NULL),
(62, 'Storaro - Escrever com a luz', '00/00/0000', '00/00/0000', '09:00:00', '09:00:00', 'Pedro Maia', '0,00', 'Diretor de fotografia italiano e responsável por filmes como "Apocalipse Now" e "O Último Tango em Paris", Vittorio Storaro desembarca no Pavilhão da Oca com exposição de fotografias inéditas no Brasil, composta por 70 imagens produzidas a partir de sobreposições de obras cinematográficas.', 'YYNGSUP4IN6MKT1FUDKTQ0XBD3Q3UCZPYRS.jpg;SAWZM2DTQ3MT68YUWOSXRX5C1IYAI1SKBAN.jpg', '0MLWK4I1G1QQPDLM5X2P3DCVMZ6IHL0VLEK.jpg', '02145-999', 'Sao Paulo', 'Sao Paulo', 'Ibirapuera', 'Pavilhão da OCA', 'Parque do Ibirapuera - Portões 2 e3, Avenida Pedro Álvares Cabral', '(11) 5524-9855', '(11) 96587-8855', 'yurioliveira@gmail.com', 'Exposicao;Festival;Outros', 'null', 0, 0, 0, 91, 45, 0, NULL, NULL),
(63, 'Boca, pra que te quero ?', '2017-11-19', '2017-12-17', '08:00:00', '21:00:00', 'Daniel Vasconcelos', '0.00', 'O local será transformado em uma grande experiência tecnológica e artística relacionado ao sorriso humano, saúde, bem-estar social e diversidade cultural.\r\nAs instalações envolvem realidade aumentada por projeção holográfica, performance VJing, totens interativos fotográficos, videowall, videoarte e vídeos filmados em 360 graus. O público poderá utilizar um sistema composto por óculos imersivo de visualização em realidade virtual.\r\n', 'YYNGSUP4IN6MKT1FUDKTQ0XBD3Q3UCZPYRS.jpg;SAWZM2DTQ3MT68YUWOSXRX5C1IYAI1SKBAN.jpg', '0MLWK4I1G1QQPDLM5X2P3DCVMZ6IHL0VLEK.jpg', '06485-999', 'Sao Paulo', 'Sao Paulo', 'Centro', 'Sesc 24 de Maio', 'Rua 24 de Maio, 109, Centro', '(11)9585-8866', '(11)98565-9855', 'yurioliveira@gmail.com', 'Exposicao;Festival;Outros', '460.284.908-30', 0, 0, 0, 111, 96, 0, NULL, NULL),
(64, 'Ra-tim-bum - O castelo', '2017-03-31', '2018-25-04', '09:00:00', '21:00:00', 'Fernanda Vieira', '20.00', 'Depois de invadir o MIS em 2014, o Castelo Rá-Tim-Bum vai reabrir as portas em outro museu de São Paulo. Até o dia 4 de fevereiro de 2018, o Memorial da América Latina recebe a mostra "Rá-Tim-Bum, O Castelo", em parceria com a TV Cultura.\r\nO grande diferencial da exposição no Memorial é que o público terá a chance de entrar num Castelo idêntico ao da série, construído numa área de 700m² anexa ao Pavilhão da Criatividade. O espaço reproduz com o máximo de fidelidade o desenho original do seriado, desde o lado externo – porta, torre com 15 metros de altura, bandeira tremulando, janelas, colunas e catavento – até a cenografia dos ambientes interiores.\r\n', 'YYNGSUP4IN6MKT1FUDKTQ0XBD3Q3UCZPYRS.jpg;SAWZM2DTQ3MT68YUWOSXRX5C1IYAI1SKBAN.jpg', '0MLWK4I1G1QQPDLM5X2P3DCVMZ6IHL0VLEK.jpg', '02195-666', 'Sao Paulo', 'Sao Paulo', 'Centro', 'Memorial da América Latina', 'Avenida Auro Soares de Moura Andrade, 664', '(11)6699-8544', '(11)96587-6633', 'yurioliveira@gmail.com', 'Exposicao;Festival;Outros', '460.284.908-30', 0, 0, 0, 562, 456, 0, NULL, NULL),
(65, 'Bijoias', '20/17/1207', '20/17/1208', '09:00:00', '09:00:00', 'Sara Alves', '0,00', 'Evento voltado á pequenos e grandes empresários, que tem como objetivo expandir seus negócios. \r\nNo local ocorrerá cerca de 180 exposições, com diferentes tipos de acessórios femininos. \r\n', 'SG2YPGRKW7290XQMIG46IC2J83P565TZTNL.jpg;OKAG25BAYODJVACVQ9Y2CQWUEFJABKCQHVZ.jpg', '5NY3BJ0UCUTFF6G830AMKVPGAUBSP32ZN66.jpg', '01465-666', 'Sao Paulo', 'Sao Paulo', 'Centro', ' Centro de Convenções Frei Caneca', 'Rua Manoel Bandeira, 08', '(11) 5548-9877', '(11) 96587-5566', 'gabrielmoreira@gmail.com', 'Feiras', 'null', 0, 0, 1, 58, 51, 0, NULL, NULL),
(66, 'Feira do bebe e gestante', '2017-12-07', '2017-12-12', '10:00:00', '22:00:00', 'Renata Castro', '7.00', 'O evento tem como objetivo fornecer os melhores preços para mães que buscam bons produtos para seus filhos, tanto recém-nascidos, quanto crianças até 12 anos de idade.', 'SG2YPGRKW7290XQMIG46IC2J83P565TZTNL.jpg;OKAG25BAYODJVACVQ9Y2CQWUEFJABKCQHVZ.jpg', '5NY3BJ0UCUTFF6G830AMKVPGAUBSP32ZN66.jpg', '02132-999', 'Sao Paulo', 'Sao Paulo ', 'Centro', 'Expo Center Norte', 'Rua Bento Branco, 32', '(11)5568-9856', '(11)96587-3321', 'gabrielmoreira@gmail.com', 'Feiras', '125455888-98', 0, 0, 1, 100, 79, 0, NULL, NULL),
(67, 'Pet Fair - Inovacao do mundo animal ', '2017-11-17', '2017-11-19', '10:00:00', '20:00:00', 'Solange Dantas', '20.00', 'Feira voltada para o público que deseja adquirir um animal de estimação, ou se caso já possua um pet, adquirir produtos e serviços para o mesmo. Cerca de 80 expositores estarão no local, prestando serviços e divulgando seus produtos.', 'SG2YPGRKW7290XQMIG46IC2J83P565TZTNL.jpg;OKAG25BAYODJVACVQ9Y2CQWUEFJABKCQHVZ.jpg', '5NY3BJ0UCUTFF6G830AMKVPGAUBSP32ZN66.jpg', '02145-999', 'Sao Paulo ', 'Sao Jose dos Campos ', 'Itabira', 'Pavilhao Central ', 'Rua Amaral Bueno, 98', '(11)5585-9966', '(11)96632-8844', 'gabrielmoreira@gmail.com', 'Feiras', '444555666-89', 0, 0, 1, 82, 74, 0, NULL, NULL),
(68, 'Sem paradinha', '2017-11-22', '2017-11-23', '22:00:00', '04:00:00', 'Flavia Monteiro ', '15.00', 'Na casa noturna você tem um leque de opções para aproveitar a noite: jogos, restaurante e pista de dança em três ambientes descolados. Às terças, rola a festa Sem Paradinha, evento que junta funk, pop, black e sucessos que estão bombando nas rádios. ', 'SG2YPGRKW7290XQMIG46IC2J83P565TZTNL.jpg;OKAG25BAYODJVACVQ9Y2CQWUEFJABKCQHVZ.jpg', '5NY3BJ0UCUTFF6G830AMKVPGAUBSP32ZN66.jpg', '02678-888', 'Sao Paulo', 'Sao Paulo', 'Centro', 'Blitz Haus ', 'Rua Augusta, 657, SP', '(11)5587-9966', '(11)96587-6522', 'gabrielmoreira@gmail.com', 'Festas', '455221325-98', 0, 0, 1, 256, 210, 0, NULL, NULL),
(69, 'Fodassyl Crew ', '2017-11-25', '2017-11-26', '23:00:00', '05:00:00', 'Bruna Bastos', '30.00', 'A festa Squad é o point do hip hop, rap, funk e outros ritmos agitados às quintas na Lions. A balada conta com varanda ampla e uma decoração moderníssima - a estrutura de qualidade é, com certeza, um diferencial da casa. ', 'SG2YPGRKW7290XQMIG46IC2J83P565TZTNL.jpg;OKAG25BAYODJVACVQ9Y2CQWUEFJABKCQHVZ.jpg', '5NY3BJ0UCUTFF6G830AMKVPGAUBSP32ZN66.jpg', '03256-999', 'Sao Paulo', 'Sao Paulo', 'Centro', 'Lions Nightclub ', 'Av. Brigadeiro Luís Antonio, 277, SP', '(11)5565-9888', '(11)96632-5411', 'gabrielmoreira@gmail.com', 'Festas', '452147789-98', 0, 0, 1, 321, 199, 0, NULL, NULL),
(70, 'Festa Los Britanicos ', '2017-11-25', '2017-11-26', '22:00:00', '06:00:00', 'Daniela Vasquez', '25.00', 'Festas incríveis ocupam o ambiente que um dia já foi um cinema no bairro da Liberdade. A música depende muito da proposta da balada, mas ritmos brasileiros, rock e pop são de lei na maioria das festas. ', 'SG2YPGRKW7290XQMIG46IC2J83P565TZTNL.jpg;OKAG25BAYODJVACVQ9Y2CQWUEFJABKCQHVZ.jpg', '5NY3BJ0UCUTFF6G830AMKVPGAUBSP32ZN66.jpg', '02145-999', 'Sao Paulo', 'Sao Paulo', 'Centro', 'Cine Joia ', 'Praça Carlos Gomes, 82, SP', '(11)5566-9988', '(11)96574-1121', 'gabrielmoreira@gmail.com', 'Festas', '223654778-99', 0, 0, 1, 489, 321, 0, NULL, NULL),
(71, '4 º Festival Internacional de Cervejas Artesanais', '20/17/1202', '20/17/1204', '12:00:00', '12:00:00', 'Ana Vitoria Marques', '190,00', 'Festival voltado a divulgação de 70 tipos de cervejas artesanais, com o intuito de beneficiar o lucro de seus produtores e abranger o conhecimento das pessoas em relação á bebidas.', 'LRS6PS6GCJRCKWG73ETI8R4E7KVHCZCJAD9.jpg;RGWWJBPO245F0NHHR8CF5F7MW9B2ZK6KQ7Q.jpg', '3TTK6IMVMXMJQ5FP0HQMQLKVP3TK7IFTYZ9.jpg', '01456-877', 'Sao Paulo', 'Campos do Jordao', 'Vila Nova', 'Pavilhao Vilela', 'Rua General Gouveia, 3445', '(11) 5564-8778', '(11) 96632-8854', 'victormota@gmail.com', 'Festival', 'null', 0, 0, 0, 99, 69, 0, NULL, NULL),
(72, 'Lollapalooza - 2018', '2018-03-23', '2018-03-25', '12:00:00', '03:00:00', 'Tadeu Bitencourt', '300.00', 'O festival terá como atrações Pearl Jam, Red Hot Chili Peppers, The Killers,\r\nImagine Dragons, Wiz Khalifa, Kygo, DJ Snake, dentre outros artistas.\r\n', 'LRS6PS6GCJRCKWG73ETI8R4E7KVHCZCJAD9.jpg;RGWWJBPO245F0NHHR8CF5F7MW9B2ZK6KQ7Q.jpg', '3TTK6IMVMXMJQ5FP0HQMQLKVP3TK7IFTYZ9.jpg', '02512-966', 'Sao Paulo', 'Sao Paulo ', 'Interlagos ', 'Autodromo de Interlagos', 'Av. Sen. Teotônio Vilela, 261 ', '(11)5544-1163', '(11)96658-8855', 'victormota@gmail.com', 'Festival', '555236985-63', 0, 0, 0, 1006, 963, 0, NULL, NULL),
(73, 'XXXPerience Festival ', '2017-12-02', '2017-12-03', '16:00:00', '04:00:00', 'Caio Bastos', '145.00', 'O evento é um dos mais esperados do ano pelos fãs da música eletrônica e promete ser memorável. Entre as atrações, nomes de peso como Cat Dealers, Astrix e Illusionize já foram confirmados.', 'LRS6PS6GCJRCKWG73ETI8R4E7KVHCZCJAD9.jpg;RGWWJBPO245F0NHHR8CF5F7MW9B2ZK6KQ7Q.jpg', '3TTK6IMVMXMJQ5FP0HQMQLKVP3TK7IFTYZ9.jpg', '02198-563', 'Sao Paulo', 'Sao Paulo', 'Vila Nova', 'Fazenda Maeda', 'Rodovia Santos Dumont (SP-75), Km 18 Sul', '(11)5568-9656', '(11)96587-6523', 'victormota@gmail.com', 'Festival ', '654789444-85', 0, 0, 0, 453, 489, 0, NULL, NULL),
(74, 'Juliana’s Graduation', '2017-12-01', '2017-12-01', '20:00:00', '23:00:00', 'Juliana Sousa', '0.00', 'Venham prestigiar esse momento único da minha vida, onde receberei meu certificado de Médica veterinária, pela USP. ', 'LRS6PS6GCJRCKWG73ETI8R4E7KVHCZCJAD9.jpg;RGWWJBPO245F0NHHR8CF5F7MW9B2ZK6KQ7Q.jpg', '3TTK6IMVMXMJQ5FP0HQMQLKVP3TK7IFTYZ9.jpg', '03265-888', 'Sao Paulo', 'Sao Paulo', 'Jardim Paulista', 'Espaço Hotel Unique ', 'Av. Brigadeiro Luís Antônio, 4700 ', '(11)5566-8852', '(11)96658-8855', 'victormota@gmail.com', 'Formatura', '654111235-98', 0, 0, 0, 156, 112, 0, NULL, NULL),
(75, 'Gabriel’s Graduation', '2017-12-08', '2017-12-08', '21:00:00', '23:00:00', 'Gabriel Moreira', '0.00', 'Venham prestigiar esse momento único da minha vida, onde receberei meu certificado de Técnico em Informática, pela Escola Senai Ary Torres. ', 'LRS6PS6GCJRCKWG73ETI8R4E7KVHCZCJAD9.jpg;RGWWJBPO245F0NHHR8CF5F7MW9B2ZK6KQ7Q.jpg', '3TTK6IMVMXMJQ5FP0HQMQLKVP3TK7IFTYZ9.jpg', '02365-888', 'Sao Paulo', 'Sao Paulo', 'Boa Vista', 'Espaço Casarão Boa Vista ', 'Rua Conselheiro Ramalho, 899', '(11)5563-9888', '(11)96658-3322', 'victormota@gmail.com', 'Formatura', '411214987-12', 0, 0, 0, 98, 81, 0, NULL, NULL),
(76, 'Yuri’s Graduation', '2017-12-15', '2017-12-15', '20:00:00', '23:00:00', 'Yuri Oliveira', '0.00', 'Venham prestigiar esse momento único da minha vida, onde receberei meu certificado de Segurança da Informação, pela FATEC SP. ', 'LRS6PS6GCJRCKWG73ETI8R4E7KVHCZCJAD9.jpg;RGWWJBPO245F0NHHR8CF5F7MW9B2ZK6KQ7Q.jpg', '3TTK6IMVMXMJQ5FP0HQMQLKVP3TK7IFTYZ9.jpg', '01245-888', 'Sao Paulo', 'Sao Paulo', 'Interlagos', 'Espaço Grand Hyatt ', 'Av. das Nações Unidas, 13301', '(11)5541-6233', '(11)99685-8966', 'victormota@gmail.com', 'Formatura', '412455321-98', 0, 0, 0, 62, 74, 0, NULL, NULL),
(77, 'Jantar Beneficente para os amigos.', '20/17/1103', '20/17/1104', '19:00:00', '19:00:00', 'Flavio Vitor', '10,00', 'Um evento patrocinado pelo próprio restaurante, para aqueles que são amigos que sempre estão juntos e, principalmente visitam ao nosso restaurante; ', 'N1QMMPPVEI4BXJUKZTMCRR4NY1UU7CKC1BM.jpg;R872MH1H8BOQKVYXC75KP14PN8HPQTXBK3Y.jpg', 'L04ZLFO01E9GGVS3PXMCAU4FB5GVU06ZF07.jpg', '02365-899', 'Sao Paulo', 'Campinas', 'Cambui', 'AMAO GASTRONOMIA', 'Rua Santo Antônio, 382 ', '(11) 5562-3200', '(11) 96632-8544', 'fernandamorais@gmail.com', 'Gastronomia', 'null', 0, 0, 0, 46, 39, 0, NULL, NULL),
(78, 'Settimana Della Cucina Regionale Italiana 2017', '2017-11-23', '2017-11-29', '18:00:00', '22:00:00', 'Bianca Moreira', '80.00', 'São Paulo recebe a 6ª edição da Settimana della Cucina Regionale Italiana, evento que traz a divulgação da gastronomia italiana com pratos preparados especialmente para o evento. ', 'N1QMMPPVEI4BXJUKZTMCRR4NY1UU7CKC1BM.jpg;R872MH1H8BOQKVYXC75KP14PN8HPQTXBK3Y.jpg', 'L04ZLFO01E9GGVS3PXMCAU4FB5GVU06ZF07.jpg', '03256-999', 'Sao PAulo', 'Sao Paulo', 'Pinheiros', 'Restaurante Regionale Italiana ', 'Av. Coronel Sezefredo Fagundes , 25.100 ', '(11)5541-6633', '(11)95685-8844', 'fernandamorais@gmail.com', 'Gastronomia', '654123333-98', 0, 0, 0, 85, 81, 0, NULL, NULL),
(79, 'Festival da Linguiça Vegana', '2017-11-26', '2017-11-26', '12:00:00', '20:00:00', 'Eduarda Santos', '0.00', 'O bairro da Vila Mariana recebe o Festival da Linguiça Vegana, com ótimas opções para quem não consome alimentos de origem animal. O evento tem entrada gratuita. Entrada e atividades gratuitas (comes e bebes à parte).', 'N1QMMPPVEI4BXJUKZTMCRR4NY1UU7CKC1BM.jpg;R872MH1H8BOQKVYXC75KP14PN8HPQTXBK3Y.jpg', 'L04ZLFO01E9GGVS3PXMCAU4FB5GVU06ZF07.jpg', '05498-666', 'Sao Paulo', 'Sao Paulo', 'Vila Mariana', 'Restaurante Avila ', 'Rua Joaquim Távora, 605', '(11)5547-6663', '(11)98547-6632', 'fernandamorais@gmail.com', 'Gastronomia', '445214555-98', 0, 0, 0, 63, 61, 0, NULL, NULL),
(80, 'LiterCultura', '2017-12-14', '2017-12-18', '19:00:00', '22:00:00', 'Maira Lopez', '0.00', 'O escritor é, antes de tudo, um leitor. Dentro desse espírito, convidamos cinco escritores e ensaístas brasileiros para discutir suas referencias, através do imaginário da literatura, no continente hispano-americano.\r\nVladimir Safatle, Julián Fuks, Marcia Tiburi, Manuel da Costa Pinto e Christian Dunker discutem, respectivamente, as obras de Roberto Bolaño, Juan José Saer, Juan Rulfo, Roberto Alrt e Adolfo Bioy Casares, abordando as questões estéticas, sociais e políticas que atravessaram suas histórias e que nos ajudam a compreender o caráter intermitente do diálogo entre o Brasil e os demais países do continente.\r\nNao podera perder este grande evento.', 'N1QMMPPVEI4BXJUKZTMCRR4NY1UU7CKC1BM.jpg;R872MH1H8BOQKVYXC75KP14PN8HPQTXBK3Y.jpg', 'L04ZLFO01E9GGVS3PXMCAU4FB5GVU06ZF07.jpg', '02158-999', 'Sao Paulo', 'Sao Paulo', 'Centro', 'Expo Literatura', 'Rua Conselheiro Laurindo, 273 ', '(11)5562-9865', '(11)98956-6325', 'fernandamorais@gmail.com', 'Literatura', '421547852-98', 0, 0, 0, 46, 41, 0, NULL, NULL),
(81, 'Evento Literario', '2017-11-25', '2017-11-25', '16:00:00', '18:00:00', 'Helen Rocha', '0.00', 'O Parque Vicentina Aranha, em São José dos Campos, recebe nesse fim de semana a quarta edição da Festa Literomusical (Flim).\r\nO evento é gratuito e tem como atrações escritores, apresentações teatrais, mesa literária. Neste sábado entre as princiais atrações estão o romancista, contista, cronista, jornalista Ignácio de Loyola Brandão e também a autora de quadrinhos, cartuns e charces Laerte Coutinho.\r\n', 'N1QMMPPVEI4BXJUKZTMCRR4NY1UU7CKC1BM.jpg;R872MH1H8BOQKVYXC75KP14PN8HPQTXBK3Y.jpg', 'L04ZLFO01E9GGVS3PXMCAU4FB5GVU06ZF07.jpg', '03256-888', 'Sao Paulo', 'Sao Jose dos Campos ', 'Vila Adriana', 'Parque Vicentina Aranha ', 'Rua Engenheiro Prudente Meireles de Moraes, 302 ', '(11)5541-2003', '(11)96325-8855', 'fernandamorais@gmail.com', 'Literatura', '456233888-95', 0, 0, 0, 46, 59, 0, NULL, NULL),
(82, 'Publicacao Danca Perigosa', '2017-12-09', '2017-12-09', '18:00:00', '20:00:00', 'Gabriela Machado', '0.00', 'No dia 09 de dezembro, a jornalista Isabelle Reis vai lançar o “Dança Perigosa”, um romance policial que começa na Rocinha, comunidade que foi palco de grandes operações policiais nos últimos meses. Tem como objetivo mostrar a real situação do Rio de Janeiro.', 'N1QMMPPVEI4BXJUKZTMCRR4NY1UU7CKC1BM.jpg;R872MH1H8BOQKVYXC75KP14PN8HPQTXBK3Y.jpg', 'L04ZLFO01E9GGVS3PXMCAU4FB5GVU06ZF07.jpg', '04856-966', 'Rio de Janeiro', 'Botafogo', 'Boa Vista', 'Hamburgueria Legião Carioca', 'Avenida Mario Linhares Fonseca, 32', '(11)5566-8856', '(11)965874-5563', 'fernandamorais@gmail.com', 'Literatura', '123654444-89', 0, 0, 0, 96, 65, 0, NULL, NULL),
(99, 'Boston Party', '10/10/1000', '10/10/1000', '10:00:00', '11:11:00', 'Boston Boys', '1050', 'Estamos ai', 'SemImagem;SemImagem', 'E9P2QXYOZM7DYUO8GJT2JZB3ZTWRG7NZJ50.jpg', '05853-310', 'SP', 'São Paulo', 'Jardim Duprat', 'Teste', 'Rua Holda Botto Malanconi', '(11)0232-3232', '(12)31231-2312', 'victormota@gmail.com', 'Ao ar livre;Casamento;Cinema', '', 1, 0, 0, 0, 0, 0, '-23.6599428', '-46.7524971');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_eventosseg`
--

CREATE TABLE `tbl_eventosseg` (
  `id_Follow` int(11) NOT NULL,
  `Id_usuario` int(11) NOT NULL,
  `Id_Evento` int(11) NOT NULL,
  `Status` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tbl_eventosseg`
--

INSERT INTO `tbl_eventosseg` (`id_Follow`, `Id_usuario`, `Id_Evento`, `Status`) VALUES
(52, 8, 65, 1),
(53, 8, 73, 1),
(54, 7, 62, 1),
(55, 3, 62, 1),
(56, 3, 71, 1),
(57, 3, 76, 1),
(58, 2, 65, 1),
(59, 2, 67, 1),
(60, 2, 61, 1),
(61, 4, 77, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_seguidores`
--

CREATE TABLE `tbl_seguidores` (
  `id_Follow` int(11) NOT NULL,
  `UserPrimario` int(11) NOT NULL,
  `UserSecundario` int(11) NOT NULL,
  `Status` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tbl_seguidores`
--

INSERT INTO `tbl_seguidores` (`id_Follow`, `UserPrimario`, `UserSecundario`, `Status`) VALUES
(46, 8, 3, 1),
(47, 8, 7, 1),
(48, 7, 8, 1),
(49, 7, 3, 1),
(50, 46, 8, 1),
(51, 46, 3, 1),
(52, 3, 8, 1),
(53, 3, 46, 1),
(54, 2, 3, 1),
(55, 2, 46, 1),
(56, 4, 8, 1),
(57, 4, 2, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_tipoeventos`
--

CREATE TABLE `tbl_tipoeventos` (
  `id` int(11) NOT NULL,
  `descricao` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tbl_tipoeventos`
--

INSERT INTO `tbl_tipoeventos` (`id`, `descricao`) VALUES
(1, 'Ao Ar Livre'),
(2, 'Cinema'),
(3, 'Bar'),
(4, 'Casamento'),
(5, 'Coporativo'),
(6, 'Criancas'),
(7, 'Eventos Culturais'),
(8, 'Exposicao'),
(9, 'Feiras'),
(10, 'Festas'),
(11, 'Festival'),
(12, 'Formatura'),
(13, 'Gastronomia'),
(14, 'Literatura'),
(15, 'Restaurante'),
(16, 'Social'),
(17, 'Urbano'),
(18, 'Outros');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_usuarios`
--

CREATE TABLE `tbl_usuarios` (
  `id_User` int(11) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `Idade` int(11) NOT NULL,
  `Email` varchar(75) NOT NULL,
  `Senha` varchar(500) NOT NULL,
  `Estado` varchar(60) DEFAULT NULL,
  `Cidade` varchar(60) DEFAULT NULL,
  `Endereco` varchar(60) DEFAULT NULL,
  `CEP` varchar(9) DEFAULT NULL,
  `Genero` varchar(15) NOT NULL,
  `telCel` varchar(20) DEFAULT NULL,
  `Privado` int(1) DEFAULT '0',
  `ImagemPerfil` varchar(150) DEFAULT 'SemImagemPerfil',
  `Preferencias` varchar(100) DEFAULT 'SemPref',
  `PalavraSecret` varchar(25) DEFAULT NULL,
  `Latitude` varchar(100) DEFAULT NULL,
  `Longitude` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tbl_usuarios`
--

INSERT INTO `tbl_usuarios` (`id_User`, `Nome`, `Idade`, `Email`, `Senha`, `Estado`, `Cidade`, `Endereco`, `CEP`, `Genero`, `telCel`, `Privado`, `ImagemPerfil`, `Preferencias`, `PalavraSecret`, `Latitude`, `Longitude`) VALUES
(1, ' André Pessoa', 15, 'andrepessoa@gmail.com', '12345', 'SP', 'São Paulo', 'Rua Maria Moassab Barbour', '04849-503', 'Masculino', '(11) 92001-9467', 0, 'SemImagemPerfil', 'Eventos Culturais;Literatura;Urbano', 'cachorro', NULL, NULL),
(2, 'Fernanda Morais ', 16, 'fernandamorais@gmail.com', '12345', 'SP', 'São Paulo', 'Rua Jose de Alencar, 33', '06585100', 'Feminino', '11985455547', 0, 'O973RCWQWZXQ9OI8WPCMX5HAYSGHA10Q4TS.jpg', 'Casamento;Festivais;Outros', 'gato', NULL, NULL),
(3, 'Gabriel Moreira', 17, 'gabrielmoreira@gmail.com', '12345', 'SP', 'São Paulo', 'Avenida Maria Lopes, 98', '03265100', 'Masculino', '11965477784', 0, '57YM8AQX47SGTTYMH1XHEAEH7X6LCEKV358.jpg', 'Ar Livre;Casamento;Cinema', 'coelho', NULL, NULL),
(4, 'Gustavo Daniel ', 18, 'gustavodaniel@gmail.com', '12345', 'SP', 'São Paulo', 'Rua Miguel Vieira, 41', '06589100', 'Masculino', '11954472332', 0, 'NYEV3HPFYAHSW103XALWSOQP5CXCCPVHA9D.jpg', 'Bar;Festas;Festivais', 'passaro', NULL, NULL),
(5, 'Jonathan Bonfim', 18, 'jonathanbonfim@gmail.com', '12345', 'SP', 'São Paulo', 'Avenida Jose Paulo, 45', '01232200', 'Masculino', '11989665555', 0, 'SemImagemPerfil', 'Bar;Festas;Festivais', 'cavalo', NULL, NULL),
(6, 'Juliana Sousa ', 17, 'julianasousa@gmail.com', '12345', 'SP', 'São Paulo', 'Rua Lourdes Moreira, 63', '01445100', 'Feminino', '11965587445', 0, 'SemImagemPerfil', 'Bar;Festas;Festivais', 'abelha ', NULL, NULL),
(8, ' Yuri Oliveira', 18, 'yurioliveira@gmail.com', '12345', 'SP', 'São Paulo', 'Rua das Iriabas', '05868-840', 'Masculino', '(11) 93566-5885', 0, '5WLRY8P4RR1GA0CHE31HHF4W3P8QJJSY32F.jpg', 'Festas;Literatura;Social', 'cobra', NULL, NULL),
(46, ' Victor Mota', 18, 'victormota@gmail.com', '12345', 'SP', 'São Paulo', 'Travessa Folha Cheirosa', '05868-850', 'masculino', '(11) 55456-4564', 0, '9WOZS22FYWE4UNYW7DK76AJCY2EO162S7FO.jpg', 'Corporativo;Exposicao;Festas', 'rato', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_denuneventos`
--
ALTER TABLE `tbl_denuneventos`
  ADD PRIMARY KEY (`Id_Denun`);

--
-- Indexes for table `tbl_eventos`
--
ALTER TABLE `tbl_eventos`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_eventosseg`
--
ALTER TABLE `tbl_eventosseg`
  ADD PRIMARY KEY (`id_Follow`);

--
-- Indexes for table `tbl_seguidores`
--
ALTER TABLE `tbl_seguidores`
  ADD PRIMARY KEY (`id_Follow`);

--
-- Indexes for table `tbl_tipoeventos`
--
ALTER TABLE `tbl_tipoeventos`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_usuarios`
--
ALTER TABLE `tbl_usuarios`
  ADD PRIMARY KEY (`id_User`),
  ADD UNIQUE KEY `Email_UNIQUE` (`Email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_denuneventos`
--
ALTER TABLE `tbl_denuneventos`
  MODIFY `Id_Denun` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `tbl_eventos`
--
ALTER TABLE `tbl_eventos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;
--
-- AUTO_INCREMENT for table `tbl_eventosseg`
--
ALTER TABLE `tbl_eventosseg`
  MODIFY `id_Follow` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;
--
-- AUTO_INCREMENT for table `tbl_seguidores`
--
ALTER TABLE `tbl_seguidores`
  MODIFY `id_Follow` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;
--
-- AUTO_INCREMENT for table `tbl_tipoeventos`
--
ALTER TABLE `tbl_tipoeventos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `tbl_usuarios`
--
ALTER TABLE `tbl_usuarios`
  MODIFY `id_User` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
