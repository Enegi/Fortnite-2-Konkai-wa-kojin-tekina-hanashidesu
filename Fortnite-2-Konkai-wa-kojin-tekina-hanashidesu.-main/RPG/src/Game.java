import gameData.*;
import gameData.Character;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Game implements Serializable {
    public int continyu = 0, roomid = 3, money = 0;
    public int choiceMenu, charaChoice;
    public boolean started = false;
    public int[] itemList = new int[10], party = new int[3];
    public int[][] enemyTroop = new int[100][7];
    public int fleeChance = 80;
    public String[] passwords = new String[50];
    public Room[] rooms = new Room[100];
    public gameData.Character[] enemy = new gameData.Character[100], protag = new gameData.Character[10];
    public StatusEffect[] statusEffects = new StatusEffect[50];
    Action[] items = new Item[100];
    Action[] moves = new Move[100];
    KeyItem[] keyItems = new KeyItem[100];
    Equip[] equips = new Equip[100];

    public Game() {
        for (int i = 0; i < 100; i++) {
            rooms[i] = new Room(i);
            enemy[i] = new gameData.Character(i);
            items[i] = new Item(i);
            moves[i] = new Move(i);
            keyItems[i] = new KeyItem(i);
            for (int j = 0; j < 7; j++) {
                enemyTroop[i][j] = 0;
            }
            equips[i] = new Equip(i);
        }
        for (int i = 0; i < 10; i++) {
            itemList[i] = -1;
            protag[i] = new Character(i);
        }
        for (int i = 0; i < 50; i++) {
            statusEffects[i] = new StatusEffect(i);
            passwords[i] = "";
        }

    }

    public void defaultSave() {
        rooms[0].assignBasic(0, "", "");
        rooms[0].assignOption(0, "", new int[]{0, 0, 0, 0, 0}, new String[]{"", "", "", "", ""}, new int[]{0, 0, 0, 0, 0});
        enemy[0].assign("", "", 0, 0, 0, 0, 0, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 0);
        protag[0].assign("", "", 0, 0, 0, 0, 0, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0}, new int[]{0, 0, 0, 0});
        enemyTroop[0][0] = 0;
        passwords[0] = "";
        party[0] = 4;

        statusEffects[0].assign(1, true, "K.O.", "", "ya no puede pelear.", "puede pelear otra vez.");
        statusEffects[1].assign(8, false, "Quemadura", "recibe daño de quemadura...", "se prende en llamas.", "ha apagado el fuego.");
        statusEffects[2].assign(2, false, "Parálisis", "no pudo moverse a causa de la parálisis...", "recibe una descarga eléctrica.", "ha perdido la parálisis.");
        statusEffects[3].assign(1, false, "Congelamiento", "esta congelado...", "fue congelado.", "se ha descongelado.");
        statusEffects[4].assign(1, false, "Restringido", "esta detenido por el general...", "es atrapado por el latigo.", "se ha liberado.");
        statusEffects[5].assign(7, false, "Enloquecido", "esta confundido...", "ha perdido la razon!", "ha recuperado la cordura.");
        statusEffects[6].assign(9, false, "Ensanguijelado", "las sanguijuelas succionan el mana del enemigo...", "se lleno de sanguijuelas!", "se desprende de las sanguijuelas.");
        statusEffects[7].assign(6, true, "Maldecido", "esta maldito...", "ha sido maldecido!", "ha sido purificado.");
        statusEffects[8].assign(8, false, "Drenadoras", "es drenado de su energía vital...", "crecen raices en el enemigo!", "se ha desprendido de las raices marchitas.");
        statusEffects[9].assign(6, true, "Vinculo simbiotico", "contiene el poder del general...", "forma un vinculo con el general.", "rompe su vinculo con el general.");
        statusEffects[10].assign(1, false, "Siesta", "duerme como una roca...", "cae al suelo.", "desperto de su sueño.");
        statusEffects[11].assign(2, false, "Niebla", "fallo su ataque...", "es envuelto en la neblina.", "se ha librado de la neblina.");
        statusEffects[12].assign(8, false, "Veneno", "recibe daño por el veneno...", "es envenenado por el ataque.", "se ha curado del veneno.");
        statusEffects[13].assign(5, true, "Fractura", "se retuerce de dolor...", "se fracturó un hueso.", "se recupera de sus fracturas.");

        moves[0].assign(0, 0, "Ataque", "", "ataca!", 1, 0, 1, -1, 0, 0, 0, -1, false, 1); //no borren este, es el ataque principal

        items[0].assign("", "", "", 0, 0, -1, 0, 0, -1, 0); //de 0 a 99 son movimientos, de 100 a 199 son objetos
        items[1].assign("Poción de curación", "Huele a pipi.", "bebe la poción... y no puede evitar escupirla!", 0, -10, -1, 0, 0, -1, 0);
        items[2].assign("Pan mohoso", "Mejor que una rata... supongo.", "se come el pan mohoso y a duras penas lo puede tragar.", 0, 5, -1, 0, 0, -1, 0);
        items[3].assign("Carne de alimaña asada", "No esta tan mal una vez te acostumbras.", "se come la carne de alimaña asada.", 0, 10, -1, 0, 0, -1, 0);

        keyItems[0].assign("", "");
        keyItems[1].assign("Llave de la celda", "Una llave vieja y oxidada.");
        keyItems[2].assign("Carne de alimaña cruda", "Quizás no sepa tan mal asada...");
        keyItems[3].assign("Carne de ganado cruda", "Carne cruda de calidad media.");

        equips[0].assign("", "", 0, 0, 0, 0, 0, 0, 0);

        equips[1].assign("Sombrero magico", "Un viejo y empolvado sombrero para magos.", 0, 3, 0, 15, 0, 0, 0);
        equips[2].assign("Bata de mago", "Bata de mago.", 1, 3, 5, 10, 0, 1, 0);
        equips[3].assign("Collar Antiguo", "Un collar barato que irradia mana.", 2, 3, 0, 10, 0, 1, 0);
        equips[4].assign("Grimorio", "Un libro de hechizos para principiantes.", 3, 3, 0, 0, 1, 0, 1);

        equips[5].assign("Boina militar", "Boina del ejercito de pisos picados.", 0, 4, 5, 0, 0, 1, 0);
        equips[6].assign("Atuendo militar", "Atuendo militar del ejercito de pisos picados.", 1, 4, 10, 0, 1, 0, 0);
        equips[7].assign("Brazal", "Brazal militar del ejercito de pisos picados.", 2, 4, 0, 0, 1, 1, 0);
        equips[8].assign("Latigo de paja", "Latigo viejo hecho con paja.", 3, 4, 0, 0, 1, 0, 0);

        equips[9].assign("Craneo robusto", "Jorgelon cuenta con un tamaño de craneo mayor al promedio.", 0, 5, 10, 0, 0, 2, -1);
        equips[10].assign("Grasa extra", "Grasa corporal extra que cubre su piel.", 1, 5, 5, 0, 0, 2, -1);
        equips[11].assign("Pañuelo arrugado", "Pañuelo que limpia los restos despues de comer.", 2, 5, 0, 5, 1, 0, 0);
        equips[12].assign("Vacio", "No necesita arma, usa su boca para atacar.", 3, 5, 0, 0, 0, 0, 0);

        equips[13].assign("Cubrebocas oscuro", "Cubrebocas que brinda sigilo.", 0, 6, 5, 0, 0, 0, 2);
        equips[14].assign("Armadura basica", "Armadura que cubre solo lo esencial.", 1, 6, 10, 0, 0, 2, 0);
        equips[15].assign("Bufanda negra", "Bufanda larga y ligera que cubre a su usuario.", 2, 6, 5, 0, 0, 0, 2);
        equips[16].assign("Tanto", "Katana corta de filo pobre.", 3, 6, 0, 0, 2, 0, 0);

        equips[17].assign("Estola", "Estola basica para sacerdotes.", 0, 7, 5, 5, 0, 0, 0);
        equips[18].assign("Tunica de sacerdote", "Tunica basica para sacerdotes.", 1, 7, 5, 5, 0, 0, 0);
        equips[19].assign("Agua Bendita con glitter", "Agua bendecida por un sacerdote de 2da.", 2, 7, 0, 10, 0, 1, 0);
        equips[20].assign("Báculo mágico", "Baculo de sacerdotes aprendices.", 3, 7, 0, 10, 0, 1, 0);

        equips[21].assign("Diadema de oro", "Una diadema de oro con un rubi.", 0, 3, 5, 15, 0, 1, 0);
        equips[22].assign("Tunica de hechicero", "Tunica para magos experimentados.", 1, 3, 5, 25, 0, 2, 0);
        equips[23].assign("Anillo de oro", "Anillo comun entre los magos.", 2, 3, 0, 10, 2, 0, 0);
        equips[24].assign("Grimorio avanzado", "Un libro de hechizos portado por magos experimentados.", 3, 3, 0, 0, 3, 0, 0);

        equips[25].assign("", "", 0, 4, 0, 0, 0, 0, 0);
        equips[26].assign("", "", 1, 4, 0, 0, 0, 0, 0);
        equips[27].assign("", "", 2, 4, 0, 0, 0, 0, 0);
        equips[28].assign("", "", 3, 4, 0, 0, 0, 0, 0);

        equips[29].assign("", "", 0, 5, 0, 0, 0, 0, 0);
        equips[30].assign("", "", 1, 5, 0, 0, 0, 0, 0);
        equips[31].assign("", "", 2, 5, 0, 0, 0, 0, 0);
        equips[32].assign("", "", 3, 5, 0, 0, 0, 0, 0);

        equips[33].assign("Kabuto", "Casco tradicional japones", 0, 6, 5, 0, 1, 2, 0);
        equips[34].assign("Armadura de samurai", "Armadura tradicional japonesa", 1, 6, 10, 0, 0, 3, -1);
        equips[35].assign("Mascara de oni", "Mascara hecha con piel de un oni rojo", 2, 6, 5, 5, 0, 0, 1);
        equips[36].assign("Katana", "Katana comun de filo superior", 3, 6, 0, 0, 5, 0, -1);

        equips[37].assign("", "", 0, 7, 0, 0, 0, 0, 0);
        equips[38].assign("", "", 1, 7, 0, 0, 0, 0, 0);
        equips[39].assign("", "", 2, 7, 0, 0, 0, 0, 0);
        equips[40].assign("", "", 3, 7, 0, 0, 0, 0, 0);

        equips[41].assign("Cofia de encaje sagrada", "Una cofia de encaje de un color blanco puro", 0, 7, 10, 8, 0, 1, 3);
        equips[42].assign("Túnicas de seda", "Túnicas hechas de seda de araña, muy resistente", 1, 7, 15, 0, 0, 5, 2);
        equips[43].assign("Galleta de vainilla pura", "Una galleta que parece tener vida propia, si se presta atencion parece recitar una tonadilla", 2, 7, 3, 1, 0, 0, 0);
        equips[44].assign("Baculo aflorado", "Un baculo hecho de ramas adornado con flores. Pulsa con poder", 3, 7, 0, 15, 0, 4, 0);

        equips[45].assign("Grimorio medio", "Un libro de hechizos para magos conocedores", 3, 7, 0, 2, 2, 0, 0);

        equips[46].assign("Flor de durazno", "En plena floracion, deja un suave aroma que calma los sentidos", 2, 0, 3, 2, 0, 0, 1);
        equips[47].assign("Collar de colmillos", "Un collar de cuentas y colmillos de monstruos", 2, 0, 0, 1, 0, 3, 0);
        equips[48].assign("Espada voladora", "Una espada en la que puedes volar", 3, 0, 0, 0, 4, 0, 6);

        Battle.LEVEL_MAX = 30;
        protag[3].assign("Andrea", "Una hechicera", 70, 150, 5, 0, 5, new int[]{1, 2, 3, 4, 5, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0}, new int[]{3, 3, 3, 3});
        moves[1].assign(20, 0, "Bola de fuego", "Ataca a un enemigo y le inflige quemaduras.", "conjura una bola de fuego hacia el enemigo!", 1, 15, 2, 1, 3, 0, 0, -1, false, 1);
        moves[2].assign(15, 0, "Paralisis", "Paraliza a un solo enemigo e inflige daño.", "paraliza al enemigo!", 1, 10, 1, 2, 4, 0, 0, -1, false, 1);
        moves[3].assign(30, 0, "Congelar", "Congela a un enemigo.", "congela al enemigo!", 1, 5, 0, 3, 3, 0, 0, -1, false, 1);
        moves[4].assign(40, 0, "Gran bola de fuego", "Ataca a un enemigo y le inflige quemaduras graves.", "conjura una enorme bola de fuego hacia el enemigo!", 1, 35, 2, 1, 5, 0, 0, -1, false, 1);
        moves[5].assign(120, 0, "Trueno", "Ataca a un enemigo y le inflige paralisis.", "conjura un trueno desde el cielo hacia el enemigo!", 1, 80, 0, 2, 4, 0, 0, -1, true, 1);
        moves[6].assign(200, 20, "Tumba de nieve", "Ataca a todos los enemigos y los congela.", "usa un hechizo que no conoce.", 3, 100, 1, 3, 5, 0, 0, -1, false, 1);

        protag[4].assign("El General", "Un mago oscuro", 80, 88, 12, 0, 6, new int[]{7, 8, 9, 10, 11, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0}, new int[]{4, 4, 4, 4});
        moves[7].assign(0, 0, "Agarre de latigo", "Retiene al enemigo por 1 turno.", "atrapa al oponente con su latigo!", 1, 0, 0, 4, 1, 0, 0, -1, false, 1);
        moves[8].assign(30, 0, "Inducción a la locura", "Enloquece a un enemigo provocando que ataque de forma aleatoria.", "susurra algo al enemigo...", 1, 0, 0, 5, 5, 0, 0, -1, false, 1);
        moves[9].assign(15, 1, "Sanguijuelas", "Invoca sanguijuelas para que consuman el mana del oponente.", "invoca sanguijuelas sobre el enemigo!", 1, 1, 0, 6, 6, 0, 0, -1, false, 7);
        moves[10].assign(40, 1, "Maldición", "Coloca un sello de maldición sobre el oponente, impidiendo su curación.", "coloca un sello maldito sobre el enemigo!", 1, 0, 0, 7, 1, 0, 0, -1, false, 1);
        moves[11].assign(15, 1, "Drenadoras", "Lanza semillas a los oponentes que consumiran su energía vital.", "lanza un par de semillas a el enemigo!", 1, 0, 0, 8, 6, 0, 0, -1, false, 1);
        moves[12].assign(40, 40, "Vinculo simbiotico", "Establece un vinculo con un aliado, restaurando gran parte de su fuerza, pero impidiendo que se curen.", "pone su mano sobre su aliado...", 2, 0, 0, 9, 1, 300, 300, 2, false, 1);

        protag[5].assign("Jorgelon", "El más comelon", 225, 20, 25, 10, 1, new int[]{13, 14, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0}, new int[]{5, 5, 5, 5});
        moves[13].assign(0, 0, "Siesta", "Recupera tu salud y mana tomando una siesta.", "bosteza...", 0, 0, 0, 10, 5, 150, 50, -1, false, 1);
        moves[14].assign(20, 10, "Bala de cañon", "Salta en el aire y cae sobre los enemigos.", "da un gran salto... y cae sobre los enemigos!", 3, 65, 1, -1, 0, 0, 0, -1, false, 1);
        moves[15].assign(5, -5, "Mordisco", "Dale un mordisco al rival y recupera salud.", "se abalanza contra el enemigo y lo muerde!", 1, 0, 2, -1, 0, 0, 0, -1, true, 1);

        protag[6].assign("Jorge", "シグマエッジロード", 120, 66, 20, 3, 16, new int[]{16, 17, 18, 19, 20, 21, 22, 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0}, new int[]{6, 6, 6, 6});
        moves[16].assign(15, 0, "Apuñalada", "Aparece detras del enemigo y atacalo por sorpresa.", "crea una distracción y ataca al enemigo por la espalda!", 1, 0, 2, -1, 0, 0, 0, -1, true, 1);
        moves[17].assign(15, 0, "Cortina de niebla", "Crea una niebla espesa para entorpecer los ataques enemigos.", "hace sellos con las manos e invoca una niebla espesa...", 3, 0, 0, 11, 2, 0, 0, -1, false, 1);
        moves[18].assign(10, 0, "Asalto de shurikens", "Lanza una rafaga de shurikens hacia todos los oponentes.", "ataca!", 3, 3, 0, -1, 0, 0, 0, -1, false, 10);
        moves[19].assign(25, 0, "Cortes consecutivos", "Ataca con un combo de 3 cortes.", "realiza 3 cortes consecutivos!", 1, 0, 1, -1, 0, 0, 0, -1, false, 3);
        moves[20].assign(40, 30, "Juicio", "Concentra toda tu fuerza en un solo corte, el corte es tan potente que daña al usuario.", "tensa todos sus musculos y desenvaina en un parpadeo!", 1, 0, 6, -1, 0, 0, 0, -1, true, 1);
        moves[21].assign(96, 66, "Muerte por 1000 cortes", "Desata tu furia sobre el enemigo con incontables cortes.", "comienza la carniceria!", 1, 10, 0, -1, 0, 0, 0, -1, false, 50);
        moves[22].assign(0, 0, "Seppuku", "Atraviesa tus intestinos con tu hoja para morir con honor.", "toma su arma y la posiciona frente a el con la punta en su dirección y... se empala a si mismo!", 0, 666, 0, -1, 0, 0, 0, -1, true, 1);
        moves[23].assign(0, 0, "Meditación", "Toma un descanso para meditar y restaurar tus energías.", "pone su arma frente a el y empieza a meditar.", 0, 0, 0, -1, 0, 10, 20, 9, false, 1);


        protag[7].assign("Félix", "Una sacerdotisa mágica", 80, 100, 3, 5, 8, new int[]{24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0}, new int[]{7, 7, 7, 7});
        moves[24].assign(10, 0, "Sanación pequeña", "Sana un poco las heridas de un aliado.", "sana a su aliado.", 2, 0, 0, -1, 0, 15, 0, -1, false, 1);
        moves[25].assign(10, 0, "Restauración pequeña", "Restaura un poco la energía de un aliado.", "restaura a su aliado.", 2, 0, 0, -1, 0, 0, 15, -1, false, 1);
        moves[26].assign(30, 0, "Sanación grande", "Sana bastante las heridas de un aliado.", "sana bastante a su aliado!", 2, 0, 0, -1, 0, 40, 0, -1, false, 1);
        moves[27].assign(30, 0, "Restauración grande", "Restaura bastante la energía de un aliado.", "restaura bastante a su aliado!", 2, 0, 0, -1, 0, 0, 40, -1, false, 1);
        moves[28].assign(40, 0, "Sanación grupal", "Sana a todos tus aliados.", "sana a todo el equipo!", 4, 0, 0, -1, 0, 20, 0, -1, false, 1);
        moves[29].assign(40, 0, "Restauración grupal", "Restaura la energía de todos tus aliados.", "restaura a todo el equipo!", 4, 0, 0, -1, 0, 0, 20, -1, false, 1);
        moves[30].assign(120, 0, "Plegaria", "Reza para recibir la bendición de los dioses.", "rezo a los dioses... y los dioses respondieron!", 4, 0, 0, -1, 0, 100, 20, 0, false, 1);
        moves[31].assign(120, 0, "Castigo divino", "Reza para que los dioses impartan justicia.", "rezo a los dioses... y los dioses respondieron!", 3, 100, 0, -1, 0, 0, 0, -1, true, 1);
        moves[32].assign(60, 0, "Resurrección", "Concentra tu energía para devolverle a alguien la consciencia.", "concentra mana en su baculo y... revive a su aliado!", 2, 0, 0, -1, 0, 40, 0, 0, false, 1);
        moves[33].assign(10, 0, "Curación: Quemadura", "Cura el efecto de estado.", "cura a su aliado.", 2, 0, 0, -1, 0, 0, 0, 1, false, 1);
        moves[34].assign(10, 0, "Curación: Parálisis", "Cura el efecto de estado.", "cura a su aliado.", 2, 0, 0, -1, 0, 0, 0, 2, false, 1);
        moves[35].assign(10, 0, "Curación: Congelamiento", "Cura el efecto de estado.", "cura a su aliado.", 2, 0, 0, -1, 0, 0, 0, 3, false, 1);
        moves[36].assign(10, 0, "Curación: Vinculo", "Cura el efecto de estado.", "cura a su aliado.", 2, 0, 0, -1, 0, 0, 0, 9, false, 1);
        moves[37].assign(10, 0, "Curación: Siesta", "Cura el efecto de estado.", "cura a su aliado.", 2, 0, 0, -1, 0, 0, 0, 10, false, 1);

        enemy[1].assign("Rata", "Y la cheese", 10, 0, 5, 0, 4, new int[]{38, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 3);
        moves[38].assign(0, 0, "Mordida", "Mordida cargada de infecciones", "te muerde!", 1, 0, 1, 12, 3, 0, 0, -1, false, 1);
        enemy[2].assign("Guardia", "Antes los hombres iban a la guerra *se fuma un puro*", 40, 10, 15, 3, 5, new int[]{7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 6);
        enemy[3].assign("Murciélago", "El señor de la noche", 10, 0, 5, 0, 6, new int[]{38, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 4);
        enemy[4].assign("Prisionero loco", "Si tomas un momento para verlo a los ojos te darás cuenta que en algún momento fue un hombre como tú que no logró escapar.", 200, 50, 18, 5, 6, new int[]{39, 15, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 3);
        moves[39].assign(10, 0, "Asalto furioso", "", "grita y se abalanza contra ti!", 1, 5, 1, -1, 0, 0, 0, -1, false, 5);
        moves[40].assign(30, 0, "Abrazo de oso", "", "te abraza y te estruje con fuerza!", 1, 0, 3, 13, 1, 0, 0, -1, true, 1);



        enemyTroop[1][0] = 1;
        enemyTroop[1][1] = 1;
        enemyTroop[2][0] = 2;
        enemyTroop[3][0] = 1;
        enemyTroop[3][1] = 3;
        enemyTroop[3][2] = 1;
        enemyTroop[4][0] = 4;
        enemyTroop[4][1] = 1;


        rooms[99].assignBasic(7, "Cuarto De Prueba", "Estas en el cuarto de prueba");
        rooms[99].assignOption(0, "Conseguir dinero del banco.", new int[]{11, 7, 0, 0, 0}, new String[]{"Contraseña", "conseguiste 200 dinero!", "", "", ""}, new int[]{19, 200, 0, 0, 0});
        passwords[19] = "Password";
        rooms[99].assignOption(1, "Comprar gota de miel (5 Dinero).", new int[]{7, 3, 0, 0, 0}, new String[]{"", "", "", "", ""}, new int[]{-5, 99, 0, 0, 0});
        items[99].assign("Gota de miel", "Una gota de miel de abeja. Es muy nutritiva!", "se toma la gota de miel!", 2, 5, 0, 5, 0, -1, 0); //de 0 a 99 son movimientos, de 100 a 199 son objetos
        rooms[99].assignOption(2, "Abrir la puerta con candado", new int[]{6, 1, 0, 0, 0}, new String[]{"", "Abres el candado y entras a la puerta.", "", "", ""}, new int[]{99, 98, 0, 0, 0});
        keyItems[99].assign("Llave de prueba", "La llave de la puerta con candado del cuarto de prueba.");
        rooms[99].assignOption(3, "Agarrar un perro callejero.", new int[]{8, 0, 0, 0, 0}, new String[]{"Wau Wau! \nTraduccion: Voy a pelear a tu lado", "", "", "", ""}, new int[]{2, 0, 0, 0, 0});
        protag[1].assign("Paladin", "Un paladin paladinico.", 10, 10, 2, 0, 1, new int[]{99, 98, 97, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0}, new int[]{0, 0, 0, 0});
        protag[2].assign("Perro Callejero", "Un perro callejero.", 10, 5, 2, 0, 3, new int[]{94, 95, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0}, new int[]{0, 0, 0, 0});
        rooms[99].assignOption(4, "Agarrar la llave de prueba.", new int[]{2, 4, 0, 0, 0}, new String[]{"Unos enemigos de prueba estan protegiendo la llave de prueba!", "", "", "", ""}, new int[]{99, 99, 0, 0, 0});
        rooms[99].assignOption(5, "Guardar el juego.", new int[]{14, 0, 0, 0, 0}, new String[]{"", "", "", "", ""}, new int[]{0, 0, 0, 0, 0});
        enemy[99].assign("Enemigo De Prueba", "Un tipo de maniqui extraño.", 5, 2, 1, 0, 2, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 3);
        enemyTroop[99][0] = 99;
        enemyTroop[99][1] = 99;
        enemyTroop[99][2] = 99;
        moves[99].assign(5, 0, "Curacion", "", "usa su conocimiento medico!", 4, 0, 0, -1, 0, 5, 0, 0, false, 1);
        moves[98].assign(10, 0, "Revivir", "", "reza y es escuchado!", 2, 0, 0, -1, 0, 5, 0, 1, false, 1);
        moves[97].assign(5, 0, "Espiral Infinito De Espadas", "", "mueve su espada de una manera que la mente mortal no puede entender!", 3, 0, 3, -1, 0, 0, 0, 0, false, 1);
        moves[94].assign(2, 0, "Mordida Envenenada", "", "muerde sin lavarse los dientes!", 1, 0, 1, 49, 3, 0, 0, 0, false, 1);
        moves[95].assign(2, 0, "Mordida Triple", "", "muerde tres veces!", 1, 0, 1, -1, 0, 0, 0, 0, false, 3);
        statusEffects[49].assign(8, false, "Infeccion", "sufre de su mordida infectada.", "tiene una mordida infectada", "se mejora.");

        rooms[98].assignBasic(2, "Fin Del Universo", "Estas en el fin del universo...");
        rooms[98].assignSingleOption(0, 0, "Regresar al cuarto de prueba.", 1, "Regresas al cuarto de prueba", 99);
        rooms[98].assignSingleOption(1, 0, "Terminar el juego.", 16, "Creditos: \nTodo: yo", 0);

        rooms[2].assignBasic(11, "Nut room", "Ayo is that the nut room??");
        rooms[2].assignOption(0, "Nuez", new int[]{0, 0, 0, 0, 0}, new String[]{"", "", "", "", ""}, new int[]{0, 0, 0, 0, 0});

        equips[99].assign("Fedora", "El favorito de los reditores", 0, 5, 0, 0, 0, 0, -1);
      
        rooms[3].assignBasic(6, "Jaula", "Una celda fria y oscura... \n Hay un latigo justo fuera de los barrotes.");
        rooms[3].assignOption(0, "Descansar en la cama", new int[]{10, 0, 0, 0, 0}, new String[]{"Duermes en la cama unas horas...", "Despiertas lleno de energía!", "", "", ""}, new int[]{0, 0, 0, 0, 0});
        rooms[3].assignOption(1, "Revisar debajo de la cama", new int[]{0, 2, 7, 4, 9}, new String[]{"Encuentras un par de monedas...", "Pero unas ratas saltan hacia ti!", "Encontraste 10 monedas!", "Conseguiste carne de alimaña cruda!", ""}, new int[]{0, 1, 10, 2, 1});
        rooms[3].assignOption(2, "Tomar el latigo a traves de los barrotes", new int[]{0, 0, 5, 9, 0}, new String[]{"Ves el latigo con el que te torturan a traves de los barrotes al lado de la silla en la que esta sentado el guardia", "Intentas alcanzarlo a traves de los barrotes...", "Conseguiste el latigo de paja!", "", ""}, new int[]{0, 0, 8, 1, 0});
        rooms[3].assignOption(3, "Abrir la celda", new int[]{6, 9, 1, 0, 0}, new String[]{"Intentas abrir la celda...", "Abres la puerta sin problemas.", "", "", ""}, new int[]{1, 0, 4, 0, 0});
        rooms[3].assignOption(4, "Llamar la atención del guardia", new int[]{0, 2, 7, 4, 9}, new String[]{"Pretendes estar muerto...", "El guardia entra a revisarte y atacas por sorpresa!", "Conseguiste 40 monedas!", "", ""}, new int[]{0, 2, 40, 1, 1});

        rooms[4].assignBasic(6, "Mazmorras", "La mayoría no salen con vida de estas mazmorras... \nVes un grupo de ratas rodeando algo.");
        rooms[4].assignOption(0, "Regresar a tu celda", new int[]{1, 0, 0, 0, 0}, new String[]{"Regresas a tu celda.", "", "", "", ""}, new int[]{3, 0, 0, 0, 0});
        rooms[4].assignOption(1, "Acercarse a las ratas", new int[]{0, 2, 3, 4, 9}, new String[]{"Te acercas a las ratas...", "Pero te atacan!", "", "Conseguiste carne de alimaña cruda!", ""}, new int[]{0, 1, 2, 2, 1});
        rooms[4].assignOption(2, "Tomar el latigo a traves de los barrotes", new int[]{0, 0, 5, 9, 0}, new String[]{"Ves el latigo con el que te torturan a traves de los barrotes al lado de la silla en la que esta sentado el guardia", "Intentas alcanzarlo a traves de los barrotes...", "Conseguiste el latigo de paja!", "", ""}, new int[]{0, 0, 8, 1, 0});
        rooms[4].assignOption(3, "Abrir la celda", new int[]{6, 9, 1, 0, 0}, new String[]{"Intentas abrir la celda...", "Abres la puerta sin problemas.", "", "", ""}, new int[]{1, 0, 4, 0, 0});
        rooms[4].assignOption(4, "Llamar la atención del guardia", new int[]{0, 2, 7, 4, 9}, new String[]{"Pretendes estar muerto...", "El guardia entra a revisarte y atacas por sorpresa!", "Conseguiste 40 monedas!", "", ""}, new int[]{0, 2, 40, 1, 1});
    }
    public void play() {
        started = true;
        while (continyu == 0) {

            rooms[roomid].optionsCheck();

            System.out.println(rooms[roomid].desc);
            for (int i = 0; i < 10; i++) {
                if (!rooms[roomid].optionName[i].isEmpty()) {
                    System.out.println((i + 1) + ". " + rooms[roomid].optionName[i]);
                }
            }
            int choice = Common.choice(rooms[roomid].options) - 1;
            if ((choice <= 9) && (choice >= 0)) {
                for (int a = 0; a < 5; a++) {
                    if (!rooms[roomid].effectText[a][choice].isEmpty()) {
                        System.out.println(rooms[roomid].effectText[a][choice]);
                    }
                    switch (rooms[roomid].effect[a][choice]) {
                        case 0: //texto vacio
                            break;
                        case 1:
                            roomid = rooms[roomid].effectID[a][choice]; //esta opcion te lleva a otro cuarto
                            break;
                        case 2: // battle
                            Battle battle = new Battle(enemy, protag, party, enemyTroop[rooms[roomid].effectID[a][choice]], statusEffects, itemList, moves, items, fleeChance);
                            switch (battle.battleEnd) {
                                case 1:
                                    continyu = 1;
                                case 2:
                                    a = 5;
                                    break;
                                case 3:
                                    break;
                            }
                            itemList = battle.itemList;
                            if (!battle.getBattler(0).name.isEmpty()) protag[party[0]] = battle.getBattler(0);
                            if (!battle.getBattler(1).name.isEmpty()) protag[party[1]] = battle.getBattler(1);
                            if (!battle.getBattler(2).name.isEmpty()) protag[party[2]] = battle.getBattler(2);
                            break;
                        case 3: //give item
                            if (Common.actuallyUsedSpace(itemList, -1) <= 9) {
                                itemList[Common.actuallyUsedSpace(itemList, -1)] = (rooms[roomid].effectID[a][choice]);
                            } else System.out.println("Pero tu inventario ya estaba lleno!");
                            break;
                        case 4: //give key item
                            keyItems[rooms[roomid].effectID[a][choice]].counter++;
                            break;
                        case 5: //give armor
                            equips[rooms[roomid].effectID[a][choice]].counter++;
                            break;
                        case 6: //this option to check for key items
                            if (keyItems[rooms[roomid].effectID[a][choice]].counter > 0)
                                keyItems[rooms[roomid].effectID[a][choice]].counter -= 1;
                            else a = 5;
                            break;
                        case 7: //this option to give or take away money
                            if ((money + rooms[roomid].effectID[a][choice]) >= 0) {
                                money += rooms[roomid].effectID[a][choice];
                            } else {
                                System.out.println("Eres demasiado pobre para eso!!");
                                a = 5;
                            }
                            break;
                        case 8: //this option to add party members
                            if (party[1] == 0) {
                                party[1] = rooms[roomid].effectID[a][choice];
                                System.out.println(protag[rooms[roomid].effectID[a][choice]].name + " se unio a la partida!");
                                rooms[roomid].options -= 1;
                            } else if (party[2] == 0) {
                                party[2] = rooms[roomid].effectID[a][choice];
                                System.out.println(protag[rooms[roomid].effectID[a][choice]].name + " se unio a la partida!");
                                rooms[roomid].options -= 1;
                            } else {
                                System.out.println("¿A quien remplazar con " + protag[rooms[roomid].effectID[a][choice]].name + "?\n  1. " + protag[party[0]].name + "\n  2. " + protag[party[1]].name + "\n  3. " + protag[party[2]].name);
                                charaChoice = Common.choice(3) - 1;
                                if ((charaChoice >= 0) && (charaChoice < 3)) {
                                    party[charaChoice] = rooms[roomid].effectID[a][choice];
                                    System.out.println(protag[rooms[roomid].effectID[a][choice]].name + " se unio a la partida!");
                                }
                                //que le pasa a los personajes que han sido reemplazados
                            }
                            break;
                        case 9: //effect id = 0 -> se mantiene la opcion, y solo se quita el efecto, effect id = 1 -> se quita la opcion completamente
                            if (rooms[roomid].effectID[a][choice] == 0) rooms[roomid].assignSingleOption(choice, (a - 1), rooms[roomid].optionName[choice], 0, "", 0);
                            else if (rooms[roomid].effectID[a][choice] == 1) rooms[roomid].assignOption(choice, "", new int[]{0, 0, 0, 0, 0}, new String[]{"", "", "", "", ""}, new int[]{0, 0, 0, 0, 0});
                            break;
                        case 10: //regenerate all health
                            for (int i = 0; i < 3; i++) {
                                protag[party[i]].curHP = protag[party[i]].maxHP;
                                protag[party[i]].curMP = protag[party[i]].maxMP;
                                for (int j = 0; j < 50; j++) {
                                    protag[party[i]].status1[j] = false;
                                    protag[party[i]].statusCounter1[j] = 0;
                                }
                            }
                            break;
                        case 11: //password check
                            String password = Common.S.next();
                            if (!passwords[rooms[roomid].effectID[a][choice]].equals(password)) a = 5;
                            break;
                        case 12: //luck check
                            if (!Common.RNG(rooms[roomid].effectID[a][choice])) a = 5;
                            break;
                        case 13: //idk what to put here
                            break;
                        case 14: //save file
                            if(save()){
                                System.out.println("Progreso guardado exitosamente.");
                            } else {
                                System.out.println("No se pudo guardar el juego.");
                            }
                            break;
                        case 15:
                            continyu = 1; //esto te mata
                            break;
                        case 16:
                            continyu = 3; //esto completa el juego
                            break;
                    }
                }
                Common.S.nextLine();
            } else {
                int continyuMenu = 1;
                while (continyuMenu == 1) {
                    System.out.println("Menu De Pausa:\n  1. Estado\n  2. Objetos\n  3. Objetos Clave\n  4. Armadura\n  5. Tecnicas\n  6. Salir del juego");
                    choiceMenu = Common.choice(6);
                    switch (choiceMenu) {
                        case 1:
                            continyuMenu++;
                            while (continyuMenu == 2) {
                                System.out.println("¿Ver el estado de quien?");
                                for (int i = 0; i < 3; i++) {
                                    if (!protag[party[i]].name.isEmpty()) {
                                        System.out.println("  " + (i + 1) + ". " + protag[party[i]].name);
                                    }
                                }
                                choiceMenu = Common.choice(3) - 1;
                                switch (choiceMenu) {
                                    case 0, 1, 2:
                                        choiceMenu = party[choiceMenu];
                                        System.out.println("Nombre: " + protag[choiceMenu].name + "  Nivel: " + protag[choiceMenu].lvl + "  Exp: " + protag[choiceMenu].exp + "/" + protag[choiceMenu].expRequirement() + "  Dinero: " + money + "$" +
                                                "\nHP: " + protag[choiceMenu].curHP + "/" + protag[choiceMenu].maxHP + "    MP: " + protag[choiceMenu].curMP + "/" + protag[choiceMenu].maxMP +
                                                "\nATK: " + protag[choiceMenu].atk + "   DEF: " + protag[choiceMenu].def + "    SPD: " + protag[choiceMenu].spd +
                                                "\nCabeza: " + equips[protag[choiceMenu].equip[0]].name + "   Cuerpo: " + equips[protag[choiceMenu].equip[1]].name +
                                                "\nAccesorio: " + equips[protag[choiceMenu].equip[2]].name + "   Arma: " + equips[protag[choiceMenu].equip[3]].name);
                                        break;
                                    default:
                                        continyuMenu = 1;
                                        break;
                                }
                            }
                            break;
                        case 2:
                            continyuMenu++;
                            while (continyuMenu == 2) {
                                if (Common.actuallyUsedSpace(itemList, -1) > 0) {
                                    System.out.println("¿Cual objeto vas a usar?");
                                    for (int j = 0; j < 10; j++) {
                                        if (itemList[j] != -1) {
                                            System.out.print("  " + (j + 1) + ": " + items[itemList[j]].printSelf());
                                            if (items[itemList[j]].heal > 0)
                                                System.out.print("  HP: " + items[itemList[j]].heal);
                                            if (items[itemList[j]].healMP > 0)
                                                System.out.print("  MP: " + items[itemList[j]].healMP);
                                            if (items[itemList[j]].cure > 0)
                                                System.out.print("  cura: " + statusEffects[items[itemList[j]].cure].name);
                                            else if (items[itemList[j]].cure == 99) System.out.print("  Cura: todo");
                                            else if (items[itemList[j]].cure == 100)
                                                System.out.print("  Cura: todo y revive");
                                            if (items[itemList[j]].dmgAdd > 0)
                                                System.out.print("  Daño: " + items[itemList[j]].dmgAdd);
                                            if (items[itemList[j]].status > 0)
                                                System.out.print("  Inflige: " + statusEffects[items[itemList[j]].status].name);
                                            System.out.println();
                                        }
                                    }
                                    choiceMenu = Common.choice(Common.actuallyUsedSpace(itemList, -1)) - 1;
                                    if ((choiceMenu >= 0) && (continyuMenu < Common.actuallyUsedSpace(itemList, -1))) {
                                        if (itemList[choiceMenu] >= 0) {
                                            if ((choiceMenu < Common.actuallyUsedSpace(itemList, -1)) && (items[itemList[choiceMenu]].targetType != 1) && (items[itemList[choiceMenu]].targetType != 3)) {
                                                protag = items[itemList[choiceMenu]].takeEffect(protag, statusEffects, party[0], 1, party);
                                                itemList[choiceMenu] = -1;
                                                Common.compress(itemList, -1);
                                            } else if ((items[itemList[choiceMenu]].targetType != 1) && (items[itemList[choiceMenu]].targetType != 3))
                                                System.out.println("Ese objeto no se puede usar!");
                                        } else continyuMenu = 1;
                                    } else continyuMenu = 1;
                                } else {
                                    continyuMenu = 1;
                                }
                            }
                            break;
                        case 3:
                            for (int i = 0; i < 100; i++) {
                                if (keyItems[i].counter > 0) {
                                    System.out.println("  " + keyItems[i].name + " x" + keyItems[i].counter + "  " + keyItems[i].desc);
                                }
                            }
                            Common.S.next();
                            break;
                        case 4:
                            continyuMenu++;
                            while (continyuMenu == 2) {
                                System.out.println("¿Cambiar las armaduras de quien?");
                                for (int i = 0; i < 3; i++) {
                                    if (!protag[party[i]].name.isEmpty()) {
                                        System.out.println("  " + (i + 1) + ". " + protag[party[i]].name);
                                    }
                                }
                                choiceMenu = Common.choice(3) - 1;
                                switch (choiceMenu) {
                                    case 0, 1, 2:
                                        continyuMenu = 3;
                                        while (continyuMenu == 3) {
                                            System.out.println("La armadura de " + protag[party[choiceMenu]].name
                                                    + "\n  1. Cabeza: " + equips[protag[choiceMenu].equip[0]].name + "\n  2. Cuerpo: " + equips[protag[choiceMenu].equip[1]].name
                                                    + "\n  3. Accesorio: " + equips[protag[choiceMenu].equip[2]].name + "\n  4. Arma: " + equips[protag[choiceMenu].equip[3]].name
                                                    + "\n    ¿Cual Accesorio quieres cambiar?");
                                            int choiceMenuArmor = Common.choice(4) - 1;
                                            switch (choiceMenuArmor) {
                                                case 0, 1, 2, 3:
                                                    int armorMenuThing = 1;
                                                    int[] armorMenuArray = new int[100];
                                                    for (int i = 0; i < 100; i++) {
                                                        armorMenuArray[i] = -1;
                                                        if ((equips[i].category == choiceMenuArmor) && ((equips[i].type == protag[party[choiceMenu]].equipType[choiceMenuArmor]) || (equips[i].type == 0)) && (equips[i].counter > 0)) {
                                                            equips[i].printSelf(armorMenuThing);
                                                            armorMenuArray[armorMenuThing] = i;
                                                            armorMenuThing++;
                                                        }
                                                    }
                                                    int choiceMenuArmorB = Common.choice(armorMenuThing) - 1;
                                                    if ((choiceMenuArmorB >= 0) && (choiceMenuArmorB <= (armorMenuThing + 1))) {
                                                        protag[choiceMenu] = equips[protag[choiceMenu].equip[choiceMenuArmor]].takeaway(protag[choiceMenu]);
                                                        protag[choiceMenu] = equips[armorMenuArray[choiceMenuArmorB]].give(protag[choiceMenu]);
                                                        protag[choiceMenu].equip[choiceMenuArmor] = equips[armorMenuArray[choiceMenuArmorB]].id;
                                                        protag[choiceMenu].statCheck();
                                                    } else continyuMenu = 2;
                                                    break;
                                                default:
                                                    continyuMenu = 2;
                                                    break;
                                            }
                                        }
                                        break;
                                    default:
                                        continyuMenu = 1;
                                        break;
                                }
                            }
                            break;
                        case 5:
                            continyuMenu++;
                            while (continyuMenu == 2) {
                                System.out.println("¿Ver los movimientos de quien?");
                                for (int i = 0; i < 3; i++) {
                                    if (!protag[party[i]].name.isEmpty()) {
                                        System.out.println("  " + (i + 1) + ". " + protag[party[i]].name);
                                    }
                                }
                                choiceMenu = Common.choice(3) - 1;
                                switch (choiceMenu) {
                                    case 0, 1, 2:
                                        if (protag[party[choiceMenu]].moveCount != 0) {
                                            for (int i = 0; i < protag[party[choiceMenu]].moveCount; i++) {
                                                if (protag[party[choiceMenu]].moves[i] != 0) {
                                                    System.out.println((i + 1) + ". " + moves[protag[party[choiceMenu]].moves[i]].printSelf());
                                                }
                                            }
                                            int move = Common.choice(Common.actuallyUsedSpace(protag[choiceMenu].moves, 0) - 1) - 1;
                                            if (move >= 0) {
                                                if (protag[party[choiceMenu]].curMP >= moves[protag[party[choiceMenu]].moves[move]].cost)
                                                    protag = moves[protag[party[choiceMenu]].moves[move]].takeEffect(protag, statusEffects, party[choiceMenu], 0, party);
                                                else System.out.println("No tienes suficiente MP!");
                                            }
                                        } else {
                                            System.out.println(protag[party[choiceMenu]].name + " No Tiene Movimientos!");
                                        }
                                        break;
                                    default:
                                        continyuMenu = 1;
                                        break;
                                }
                            }
                            break;
                        case 6:
                            continyu = 2;
                        default:
                            continyuMenu = 0;
                            break;
                    }
                }
            }
        }
        switch (continyu) {
            case 1:
                System.out.println("Moriste!!! Game Over!!!");
                break;
            case 3:
                System.out.println("Ganaste El Juego!!!");
                break;
        }
    }

    private boolean save(){
        System.out.println("¿En cual archivo quieres guardar?");
        RPG.printSaves();
        int saveN = Common.choice(4);
        boolean success;
        if (saveN != 0){
            String save = "saveFile" + saveN + ".txt";
            Game temp = new Game();
            temp.continyu = continyu;
            temp.roomid = roomid;
            temp.money = money;
            temp.itemList = itemList; temp.party = party;
            temp.enemyTroop = enemyTroop;
            temp.fleeChance = fleeChance;
            temp.passwords = passwords;
            temp.rooms = rooms;
            temp.enemy = enemy;
            temp.protag = protag;
            temp.statusEffects = statusEffects;
            temp.items = items;
            temp.moves = moves;
            temp.keyItems = keyItems;
            temp.equips = equips;
            temp.started = true;
            try {
                FileOutputStream fos = new FileOutputStream(save);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(temp);
                oos.close();
                success = true;
            } catch (IOException e){
                success = false;
            }
        } else success = false;
        return success;
    }
}
