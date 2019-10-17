package com.example.moviecatalogue.utils

import com.example.moviecatalogue.R
import com.example.moviecatalogue.entity.FilmEntity

class FakeDataDummy {

    private val data = mutableListOf<FilmEntity>()

    fun generateMovieData(): MutableList<FilmEntity> {
        data.clear()

        data.add(
            FilmEntity(
                "A Star is Born",
                "A Star Is Born is a 2018 American musical romantic drama film produced and directed by Bradley Cooper and written by Eric Roth, Cooper and Will Fetters. A remake of the 1937 film of the same name, it stars Cooper, Lady Gaga, Dave Chappelle, Andrew Dice Clay, and Sam Elliott, and follows a hard-drinking musician (Cooper) who discovers and falls in love with a young singer (Gaga). It marks the third remake of the original 1937 film, after the 1954 musical and the 1976 musical.",
                R.drawable.poster_a_start_is_born,
                "October 5, 2018",
                "148 minutes",
                "Warner Bros. Pictures"
            )
        )
        data.add(
            FilmEntity(
                "Alita Battle Angel",
                "Alita: Battle Angel is a 2019 American cyberpunk action film based on the 1990s Japanese manga series Gunnm (known as Battle Angel Alita outside Japan) by Yukito Kishiro. Directed by Robert Rodriguez, the film is co-produced by James Cameron and written by Cameron and Laeta Kalogridis. Rosa Salazar stars as the titular heroine Alita, an amnesiac cyborg girl who sets out to learn about her destiny after she awakens in a new body with no past memory of who she is. Christoph Waltz, Jennifer Connelly, Mahershala Ali, Ed Skrein, Jackie Earle Haley and Keean Johnson also star in supporting roles.",
                R.drawable.poster_alita,
                "February 14, 2019",
                "122 minutes",
                "20th Century Fox"
            )
        )
        data.add(
            FilmEntity(
                "Aquaman",
                "Aquaman is a 2018 American superhero film based on the DC Comics character Aquaman and distributed by Warner Bros. Entertainment. It is the sixth installment in the DC Extended Universe (DCEU). The film is directed by James Wan, from a screenplay by David Leslie Johnson-McGoldrick and Will Beall, and is based on a story written by Geoff Johns, Wan and Beall. Aquaman stars Jason Momoa as the title character, with Amber Heard, Willem Dafoe, Patrick Wilson, Dolph Lundgren, Yahya Abdul-Mateen II, and Nicole Kidman in supporting roles. It is the third live-action theatrical film featuring Aquaman, following Batman v Superman: Dawn of Justice (2016) and Justice League (2017), and the first full-length feature film centered around the character. In the film, the titular character learns he is the heir to the underwater kingdom of Atlantis and must step forward to lead his people against his half-brother, Orm, who seeks to unite the seven underwater kingdoms against the surface world.",
                R.drawable.poster_aquaman,
                "December 21, 2018",
                "143 minutes",
                "Warner Bros. Pictures"
            )
        )
        data.add(
            FilmEntity(
                "Bohemian Rhapsody",
                "Bohemian Rhapsody is a 2018 biographical musical film, named after the song Bohemian Rhapsody, about Freddie Mercury, lead singer of the British rock band Queen. It follows the singers life from the formation of the band up to their 1985 Live Aid performance at the original Wembley Stadium. It was directed by Bryan Singer from a screenplay by Anthony McCarten, and produced by Graham King and Queen manager Jim Beach. It stars Rami Malek as Mercury, with Lucy Boynton, Gwilym Lee, Ben Hardy, Joe Mazzello, Aidan Gillen, Tom Hollander, Allen Leech, and Mike Myers in supporting roles. Queen members Brian May and Roger Taylor served as consultants. A British-American venture, the film was produced by 20th Century Fox, Regency Enterprises, GK Films, and Queen Films, with Fox serving as distributor.",
                R.drawable.poster_bohemian,
                "2 November 2018",
                "134 minutes",
                "20th Century Fox"
            )
        )
        data.add(
            FilmEntity(
                "Cold Pursuit",
                "Cold Pursuit is a 2019 American-British black comedy action film directed by Hans Petter Moland (in his Hollywood debut) from a screenplay by Frank Baldwin. The film stars Liam Neeson, Tom Bateman, Tom Jackson, Emmy Rossum, Domenick Lombardozzi, Julia Jones, John Doman, and Laura Dern. It is an official remake of the 2014 Norwegian film In Order of Disappearance (Kraftidioten), also directed by Moland, and follows a vengeful snowplow driver who starts killing the members of a drug cartel following the murder of his son.",
                R.drawable.poster_cold_persuit,
                "February 8, 2019",
                "118 minutes",
                "Summit Entertainment"
            )
        )
        data.add(
            FilmEntity(
                "Creed II",
                "Creed II is a 2018 American sports drama film directed by Steven Caple Jr., and written by Sylvester Stallone and Juel Taylor from a story by Sascha Penn and Cheo Hodari Coker. A sequel to Creed (2015) and the eighth installment in the Rocky film series, it stars Michael B. Jordan, Stallone, Tessa Thompson, Dolph Lundgren, Florian Munteanu, Wood Harris, and Phylicia Rashad. Creed writer-director Ryan Coogler serves as an executive producer on the film. The film follows a fight over 33 years in the making, as Adonis Creed meets a new adversary in the ring: Viktor Drago, son of Ivan Drago, the powerful athlete who killed Adonis father Apollo Creed.",
                R.drawable.poster_creed,
                "November 21, 2018",
                "130 minutes",
                "Warner Bros. Pictures"
            )
        )
        data.add(
            FilmEntity(
                "Fantastic Beasts: The Crimes of Grindelwald",
                "Fantastic Beasts: The Crimes of Grindelwald is a 2018 fantasy film directed by David Yates and written by J. K. Rowling. A joint British and American production, it is the sequel to Fantastic Beasts and Where to Find Them (2016). It is the second instalment in the Fantastic Beasts film series, and the tenth overall in the Wizarding World franchise, which began with the Harry Potter film series. The film features an ensemble cast that includes Eddie Redmayne, Katherine Waterston, Dan Fogler, Alison Sudol, Ezra Miller, Zoë Kravitz, Callum Turner, Claudia Kim, William Nadylam, Kevin Guthrie, Jude Law, and Johnny Depp. The plot follows Newt Scamander and Albus Dumbledore as they attempt to take down the dark wizard Gellert Grindelwald, while facing new threats in a more divided wizarding world.",
                R.drawable.poster_crimes,
                "16 November 2018",
                "134 minutes",
                "Warner Bros. Pictures"
            )
        )
        data.add(
            FilmEntity(
                "Glass",
                "Glass is a 2019 American psychological superhero thriller film written and directed by M. Night Shyamalan, who also producer with Jason Blum, Marc Bienstock and Ashwin Rajan. The film is a crossover and sequel to Shyamalans previous films Unbreakable (2000) and Split (2016), serving as the final installment in the Unbreakable trilogy.[8] Bruce Willis, Samuel L. Jackson, Spencer Treat Clark, and Charlayne Woodard reprise their Unbreakable roles, while James McAvoy and Anya Taylor-Joy return as their Split characters,[9] with Sarah Paulson, Adam David Thompson, and Luke Kirby joining the cast. In the film, David Dunn becomes locked in a mental hospital alongside his archenemy Mr. Glass, as well as the multi-personality \"The Horde\", and must contend with a psychiatrist who is out to prove the trio do not actually possess super-human abilities.",
                R.drawable.poster_glass,
                "January 18, 2019",
                "129 minutes",
                "Universal Pictures"
            )
        )
        data.add(
            FilmEntity(
                "How To Train Your Dragon",
                "How to Train Your Dragon is a 2010 American computer-animated action fantasy film loosely based on the 2003 book of the same name by British author Cressida Cowell, produced by DreamWorks Animation and distributed by Paramount Pictures. The film was directed by Chris Sanders and Dean DeBlois from a screenplay by Will Davies, Sanders, and DeBlois, and stars the voices of Jay Baruchel, Gerard Butler, Craig Ferguson, America Ferrera, Jonah Hill, Christopher Mintz-Plasse, T. J. Miller, and Kristen Wiig. The story takes place in a mythical Viking world where a young Viking teenager named Hiccup aspires to follow his tribes tradition of becoming a dragon slayer. After finally capturing his first dragon, and with his chance at last of gaining the tribes acceptance, he finds that he no longer wants to kill the dragon and instead befriends it.",
                R.drawable.poster_how_to_train,
                "March 26, 2010",
                "98 minutes",
                "Paramount Pictures"
            )
        )
        data.add(
            FilmEntity(
                "Infinity War",
                "Avengers: Infinity War is a 2018 American superhero film based on the Marvel Comics superhero team the Avengers, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures. It is the sequel to 2012s The Avengers and 2015s Avengers: Age of Ultron, and the nineteenth film in the Marvel Cinematic Universe (MCU). It was directed by Anthony and Joe Russo, written by Christopher Markus and Stephen McFeely, and features an ensemble cast including Robert Downey Jr., Chris Hemsworth, Mark Ruffalo, Chris Evans, Scarlett Johansson, Benedict Cumberbatch, Don Cheadle, Tom Holland, Chadwick Boseman, Paul Bettany, Elizabeth Olsen, Anthony Mackie, Sebastian Stan, Danai Gurira, Letitia Wright, Dave Bautista, Zoe Saldana, Josh Brolin, and Chris Pratt. In the film, the Avengers and the Guardians of the Galaxy attempt to prevent Thanos from amassing the all-powerful Infinity Stones in a bid to wipe out half of all life in the universe.",
                R.drawable.poster_infinity_war,
                "April 27, 2018",
                "149 minutes",
                "Walt Disney Studios"
            )
        )

        return data
    }

}