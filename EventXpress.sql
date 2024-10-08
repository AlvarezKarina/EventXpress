PGDMP     &                     |            EventXpress    15.3    15.3 B    E           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            F           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            G           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            H           1262    311782    EventXpress    DATABASE     �   CREATE DATABASE "EventXpress" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE "EventXpress";
                postgres    false                        2615    2200    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                postgres    false            I           0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    5            �            1259    311783    boletos    TABLE     -  CREATE TABLE public.boletos (
    id_boleto integer NOT NULL,
    fecha_compra date NOT NULL,
    cantidad_boletos integer NOT NULL,
    num_tarjeta character varying(20) NOT NULL,
    id_zona_establec integer NOT NULL,
    id_evento_progra integer NOT NULL,
    dui character varying(10) NOT NULL
);
    DROP TABLE public.boletos;
       public         heap    postgres    false    5            �            1259    311786    boletos_id_boleto_seq    SEQUENCE     �   CREATE SEQUENCE public.boletos_id_boleto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.boletos_id_boleto_seq;
       public          postgres    false    5    214            J           0    0    boletos_id_boleto_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.boletos_id_boleto_seq OWNED BY public.boletos.id_boleto;
          public          postgres    false    215            �            1259    311787 
   categorias    TABLE     l   CREATE TABLE public.categorias (
    id_categoria integer NOT NULL,
    categoria character varying(100)
);
    DROP TABLE public.categorias;
       public         heap    postgres    false    5            �            1259    311790    categorias_id_categoria_seq    SEQUENCE     �   CREATE SEQUENCE public.categorias_id_categoria_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.categorias_id_categoria_seq;
       public          postgres    false    216    5            K           0    0    categorias_id_categoria_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.categorias_id_categoria_seq OWNED BY public.categorias.id_categoria;
          public          postgres    false    217            �            1259    311791    establecimiento    TABLE     7  CREATE TABLE public.establecimiento (
    id_establecimiento integer NOT NULL,
    n_establecimiento character varying(100) NOT NULL,
    direccion character varying(100) NOT NULL,
    capacidad_establec integer,
    activo integer,
    fecha_baja character varying(20),
    fecha_alta character varying(20)
);
 #   DROP TABLE public.establecimiento;
       public         heap    postgres    false    5            �            1259    311794 &   establecimiento_id_establecimiento_seq    SEQUENCE     �   CREATE SEQUENCE public.establecimiento_id_establecimiento_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 =   DROP SEQUENCE public.establecimiento_id_establecimiento_seq;
       public          postgres    false    218    5            L           0    0 &   establecimiento_id_establecimiento_seq    SEQUENCE OWNED BY     q   ALTER SEQUENCE public.establecimiento_id_establecimiento_seq OWNED BY public.establecimiento.id_establecimiento;
          public          postgres    false    219            �            1259    311795    evento_programado    TABLE     a  CREATE TABLE public.evento_programado (
    id_evento_progra integer NOT NULL,
    fecha date NOT NULL,
    hora_inicia time without time zone NOT NULL,
    hora_fin time without time zone NOT NULL,
    suspendido boolean,
    reprogramado boolean,
    fecha_reprogramado date,
    id_evento integer NOT NULL,
    id_establecimiento integer NOT NULL
);
 %   DROP TABLE public.evento_programado;
       public         heap    postgres    false    5            �            1259    311798 &   evento_programado_id_evento_progra_seq    SEQUENCE     �   CREATE SEQUENCE public.evento_programado_id_evento_progra_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 =   DROP SEQUENCE public.evento_programado_id_evento_progra_seq;
       public          postgres    false    220    5            M           0    0 &   evento_programado_id_evento_progra_seq    SEQUENCE OWNED BY     q   ALTER SEQUENCE public.evento_programado_id_evento_progra_seq OWNED BY public.evento_programado.id_evento_progra;
          public          postgres    false    221            �            1259    311799    eventos    TABLE     �   CREATE TABLE public.eventos (
    id_evento integer NOT NULL,
    n_evento character varying(100) NOT NULL,
    descripcion text NOT NULL,
    id_categoria integer NOT NULL
);
    DROP TABLE public.eventos;
       public         heap    postgres    false    5            �            1259    311804    eventos_id_evento_seq    SEQUENCE     �   CREATE SEQUENCE public.eventos_id_evento_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.eventos_id_evento_seq;
       public          postgres    false    5    222            N           0    0    eventos_id_evento_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.eventos_id_evento_seq OWNED BY public.eventos.id_evento;
          public          postgres    false    223            �            1259    311805    usuarios    TABLE     Z  CREATE TABLE public.usuarios (
    dui character varying(10) NOT NULL,
    nombre_u character varying(100) NOT NULL,
    apellido_u character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    "contraseña" character varying(100) NOT NULL,
    fecha_nac date NOT NULL,
    genero_u character(1) NOT NULL,
    tipo_u boolean
);
    DROP TABLE public.usuarios;
       public         heap    postgres    false    5            �            1259    311808    zona_establec    TABLE     �   CREATE TABLE public.zona_establec (
    id_zona_establec integer NOT NULL,
    precio numeric(10,2) NOT NULL,
    capacidad_zona integer NOT NULL,
    id_zona integer NOT NULL,
    id_establecimiento integer NOT NULL
);
 !   DROP TABLE public.zona_establec;
       public         heap    postgres    false    5            �            1259    311811 "   zona_establec_id_zona_establec_seq    SEQUENCE     �   CREATE SEQUENCE public.zona_establec_id_zona_establec_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.zona_establec_id_zona_establec_seq;
       public          postgres    false    5    225            O           0    0 "   zona_establec_id_zona_establec_seq    SEQUENCE OWNED BY     i   ALTER SEQUENCE public.zona_establec_id_zona_establec_seq OWNED BY public.zona_establec.id_zona_establec;
          public          postgres    false    226            �            1259    311812    zonas    TABLE     m   CREATE TABLE public.zonas (
    id_zona integer NOT NULL,
    nombre_zona character varying(100) NOT NULL
);
    DROP TABLE public.zonas;
       public         heap    postgres    false    5            �            1259    311815    zonas_id_zona_seq    SEQUENCE     �   CREATE SEQUENCE public.zonas_id_zona_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.zonas_id_zona_seq;
       public          postgres    false    5    227            P           0    0    zonas_id_zona_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.zonas_id_zona_seq OWNED BY public.zonas.id_zona;
          public          postgres    false    228            �           2604    311816    boletos id_boleto    DEFAULT     v   ALTER TABLE ONLY public.boletos ALTER COLUMN id_boleto SET DEFAULT nextval('public.boletos_id_boleto_seq'::regclass);
 @   ALTER TABLE public.boletos ALTER COLUMN id_boleto DROP DEFAULT;
       public          postgres    false    215    214            �           2604    311817    categorias id_categoria    DEFAULT     �   ALTER TABLE ONLY public.categorias ALTER COLUMN id_categoria SET DEFAULT nextval('public.categorias_id_categoria_seq'::regclass);
 F   ALTER TABLE public.categorias ALTER COLUMN id_categoria DROP DEFAULT;
       public          postgres    false    217    216            �           2604    311818 "   establecimiento id_establecimiento    DEFAULT     �   ALTER TABLE ONLY public.establecimiento ALTER COLUMN id_establecimiento SET DEFAULT nextval('public.establecimiento_id_establecimiento_seq'::regclass);
 Q   ALTER TABLE public.establecimiento ALTER COLUMN id_establecimiento DROP DEFAULT;
       public          postgres    false    219    218            �           2604    311819 "   evento_programado id_evento_progra    DEFAULT     �   ALTER TABLE ONLY public.evento_programado ALTER COLUMN id_evento_progra SET DEFAULT nextval('public.evento_programado_id_evento_progra_seq'::regclass);
 Q   ALTER TABLE public.evento_programado ALTER COLUMN id_evento_progra DROP DEFAULT;
       public          postgres    false    221    220            �           2604    311820    eventos id_evento    DEFAULT     v   ALTER TABLE ONLY public.eventos ALTER COLUMN id_evento SET DEFAULT nextval('public.eventos_id_evento_seq'::regclass);
 @   ALTER TABLE public.eventos ALTER COLUMN id_evento DROP DEFAULT;
       public          postgres    false    223    222            �           2604    311821    zona_establec id_zona_establec    DEFAULT     �   ALTER TABLE ONLY public.zona_establec ALTER COLUMN id_zona_establec SET DEFAULT nextval('public.zona_establec_id_zona_establec_seq'::regclass);
 M   ALTER TABLE public.zona_establec ALTER COLUMN id_zona_establec DROP DEFAULT;
       public          postgres    false    226    225            �           2604    311822    zonas id_zona    DEFAULT     n   ALTER TABLE ONLY public.zonas ALTER COLUMN id_zona SET DEFAULT nextval('public.zonas_id_zona_seq'::regclass);
 <   ALTER TABLE public.zonas ALTER COLUMN id_zona DROP DEFAULT;
       public          postgres    false    228    227            4          0    311783    boletos 
   TABLE DATA           �   COPY public.boletos (id_boleto, fecha_compra, cantidad_boletos, num_tarjeta, id_zona_establec, id_evento_progra, dui) FROM stdin;
    public          postgres    false    214   �R       6          0    311787 
   categorias 
   TABLE DATA           =   COPY public.categorias (id_categoria, categoria) FROM stdin;
    public          postgres    false    216   T       8          0    311791    establecimiento 
   TABLE DATA           �   COPY public.establecimiento (id_establecimiento, n_establecimiento, direccion, capacidad_establec, activo, fecha_baja, fecha_alta) FROM stdin;
    public          postgres    false    218   5T       :          0    311795    evento_programado 
   TABLE DATA           �   COPY public.evento_programado (id_evento_progra, fecha, hora_inicia, hora_fin, suspendido, reprogramado, fecha_reprogramado, id_evento, id_establecimiento) FROM stdin;
    public          postgres    false    220   1U       <          0    311799    eventos 
   TABLE DATA           Q   COPY public.eventos (id_evento, n_evento, descripcion, id_categoria) FROM stdin;
    public          postgres    false    222   [V       >          0    311805    usuarios 
   TABLE DATA           p   COPY public.usuarios (dui, nombre_u, apellido_u, email, "contraseña", fecha_nac, genero_u, tipo_u) FROM stdin;
    public          postgres    false    224   �W       ?          0    311808    zona_establec 
   TABLE DATA           n   COPY public.zona_establec (id_zona_establec, precio, capacidad_zona, id_zona, id_establecimiento) FROM stdin;
    public          postgres    false    225   �Z       A          0    311812    zonas 
   TABLE DATA           5   COPY public.zonas (id_zona, nombre_zona) FROM stdin;
    public          postgres    false    227   �[       Q           0    0    boletos_id_boleto_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.boletos_id_boleto_seq', 127, true);
          public          postgres    false    215            R           0    0    categorias_id_categoria_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.categorias_id_categoria_seq', 3, true);
          public          postgres    false    217            S           0    0 &   establecimiento_id_establecimiento_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public.establecimiento_id_establecimiento_seq', 14, true);
          public          postgres    false    219            T           0    0 &   evento_programado_id_evento_progra_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public.evento_programado_id_evento_progra_seq', 64, true);
          public          postgres    false    221            U           0    0    eventos_id_evento_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.eventos_id_evento_seq', 12, true);
          public          postgres    false    223            V           0    0 "   zona_establec_id_zona_establec_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.zona_establec_id_zona_establec_seq', 39, true);
          public          postgres    false    226            W           0    0    zonas_id_zona_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.zonas_id_zona_seq', 3, true);
          public          postgres    false    228            �           2606    311824    boletos boletos_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.boletos
    ADD CONSTRAINT boletos_pkey PRIMARY KEY (id_boleto);
 >   ALTER TABLE ONLY public.boletos DROP CONSTRAINT boletos_pkey;
       public            postgres    false    214            �           2606    311826    categorias categorias_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.categorias
    ADD CONSTRAINT categorias_pkey PRIMARY KEY (id_categoria);
 D   ALTER TABLE ONLY public.categorias DROP CONSTRAINT categorias_pkey;
       public            postgres    false    216            �           2606    311828 $   establecimiento establecimiento_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.establecimiento
    ADD CONSTRAINT establecimiento_pkey PRIMARY KEY (id_establecimiento);
 N   ALTER TABLE ONLY public.establecimiento DROP CONSTRAINT establecimiento_pkey;
       public            postgres    false    218            �           2606    311830 (   evento_programado evento_programado_pkey 
   CONSTRAINT     t   ALTER TABLE ONLY public.evento_programado
    ADD CONSTRAINT evento_programado_pkey PRIMARY KEY (id_evento_progra);
 R   ALTER TABLE ONLY public.evento_programado DROP CONSTRAINT evento_programado_pkey;
       public            postgres    false    220            �           2606    311832    eventos eventos_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.eventos
    ADD CONSTRAINT eventos_pkey PRIMARY KEY (id_evento);
 >   ALTER TABLE ONLY public.eventos DROP CONSTRAINT eventos_pkey;
       public            postgres    false    222            �           2606    311834    usuarios usuarios_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (dui);
 @   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT usuarios_pkey;
       public            postgres    false    224            �           2606    311836     zona_establec zona_establec_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.zona_establec
    ADD CONSTRAINT zona_establec_pkey PRIMARY KEY (id_zona_establec);
 J   ALTER TABLE ONLY public.zona_establec DROP CONSTRAINT zona_establec_pkey;
       public            postgres    false    225            �           2606    311838    zonas zonas_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.zonas
    ADD CONSTRAINT zonas_pkey PRIMARY KEY (id_zona);
 :   ALTER TABLE ONLY public.zonas DROP CONSTRAINT zonas_pkey;
       public            postgres    false    227            �           2606    311839 ;   evento_programado evento_programado_id_establecimiento_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.evento_programado
    ADD CONSTRAINT evento_programado_id_establecimiento_fkey FOREIGN KEY (id_establecimiento) REFERENCES public.establecimiento(id_establecimiento);
 e   ALTER TABLE ONLY public.evento_programado DROP CONSTRAINT evento_programado_id_establecimiento_fkey;
       public          postgres    false    220    3219    218            �           2606    311844    eventos fk_categorias    FK CONSTRAINT     �   ALTER TABLE ONLY public.eventos
    ADD CONSTRAINT fk_categorias FOREIGN KEY (id_categoria) REFERENCES public.categorias(id_categoria);
 ?   ALTER TABLE ONLY public.eventos DROP CONSTRAINT fk_categorias;
       public          postgres    false    216    3217    222            �           2606    311849    boletos fk_dui_usuario    FK CONSTRAINT     u   ALTER TABLE ONLY public.boletos
    ADD CONSTRAINT fk_dui_usuario FOREIGN KEY (dui) REFERENCES public.usuarios(dui);
 @   ALTER TABLE ONLY public.boletos DROP CONSTRAINT fk_dui_usuario;
       public          postgres    false    3225    224    214            �           2606    311854     zona_establec fk_establecimentos    FK CONSTRAINT     �   ALTER TABLE ONLY public.zona_establec
    ADD CONSTRAINT fk_establecimentos FOREIGN KEY (id_establecimiento) REFERENCES public.establecimiento(id_establecimiento);
 J   ALTER TABLE ONLY public.zona_establec DROP CONSTRAINT fk_establecimentos;
       public          postgres    false    225    3219    218            �           2606    311859 #   evento_programado fk_eventos_progra    FK CONSTRAINT     �   ALTER TABLE ONLY public.evento_programado
    ADD CONSTRAINT fk_eventos_progra FOREIGN KEY (id_evento) REFERENCES public.eventos(id_evento);
 M   ALTER TABLE ONLY public.evento_programado DROP CONSTRAINT fk_eventos_progra;
       public          postgres    false    220    3223    222            �           2606    311864 &   boletos fk_eventos_programados_boletos    FK CONSTRAINT     �   ALTER TABLE ONLY public.boletos
    ADD CONSTRAINT fk_eventos_programados_boletos FOREIGN KEY (id_evento_progra) REFERENCES public.evento_programado(id_evento_progra);
 P   ALTER TABLE ONLY public.boletos DROP CONSTRAINT fk_eventos_programados_boletos;
       public          postgres    false    214    3221    220            �           2606    311869 %   zona_establec fk_zona_establecimiento    FK CONSTRAINT     �   ALTER TABLE ONLY public.zona_establec
    ADD CONSTRAINT fk_zona_establecimiento FOREIGN KEY (id_zona) REFERENCES public.zonas(id_zona);
 O   ALTER TABLE ONLY public.zona_establec DROP CONSTRAINT fk_zona_establecimiento;
       public          postgres    false    225    3229    227            �           2606    311874    boletos fk_zona_establecimiento    FK CONSTRAINT     �   ALTER TABLE ONLY public.boletos
    ADD CONSTRAINT fk_zona_establecimiento FOREIGN KEY (id_zona_establec) REFERENCES public.zona_establec(id_zona_establec);
 I   ALTER TABLE ONLY public.boletos DROP CONSTRAINT fk_zona_establecimiento;
       public          postgres    false    214    3227    225            4   m  x����q� ��qj��� ���_GIl�����}h�+)1�d�L�Z*�4�f..�朢$���,�A(㩎;��H�0I�D�,��K0:3-��
E���Z-Q�J*qj�܂)'Ø<����#�C٢��L��e���i�ѿ��L�J��%�th[汙���%�iሡ�>��p� Xhq��E��6��a��v^�xfl���ڮLYc�s
8'{��̴ږy�|b܆�.M����@63m���'�lC]�qf��=�����X������qe6�l?�����p�զ�o<N3RƞD��0��+�w�b���D�G�Δ�K�[/~����5[�vR�[,~ ��Н��x������2��L      6      x�3�t�2�t�2�t����� Q>      8   �   x�m�AN�@E��)|:IK�`�R	�,��$��:�ɭXq�^�D�]X��?��]P���_�Z\����-��ʍ1��re�Ua�2�&��;�o�Vx&6��� /��	��'A�7�i����q���)��a �6�q�3��O��qj�n�yT��T�����\���:��]~�a����/+�^e��>g��	��52��Bʮf�7t*�����/܇:	#�����Y�� �nO      :     x���ɍ1�b.c���F�#��a�C�a����h��VK�Y6B�'�Cp���e��O�Ђ��Jb�N�e�v!��z�̉���$���@��N��	i���!��$�w��2���:	l��տZR-7���Q������,�tG��M����~a��ck�������̕~����}��[�|L�}�5QB�lfM0���i,y�
�Ã�]?�Z�MȫS��~?l5(�M�|�.��A��Xl��gX�G��5�Ann�S��b(�ɶ`ol�� ޾/��      <   :  x���KN1���)|�Ly�%T�UY K6n�e�2��L��V����:T�R�X��/��T�
X����}+��t%s�1�&�0B�4�"�� ��R��S���nN=������zQ01�P�����+?s�(�,����ɮnd�)I�2��Us�>��������,X;��܈�Q��mo��K�Xjl����\e4��$���6/O[�z����-���]�1̃
d��l7 �2�)��7��3w�a�9��%��A�Vg.8EW6{����[-���=�:6�Ҝ�8D��T�{h	V�C�<�۸���<�� [��      >   '  x�U�;�I���]�`�^df������n�zp��ydؾ͆&�t�eI�Hh�
�k~��9�\��{��I��˿��e�t�U���e�����q�t֦%P&��r
�	r�1I�2ڀT*K�4@GᎌH.0�����ycJ�n�w�m;ܧ�8]^�F���>
��9�95`��Ր�`��iNf+s�9�p�^8S��sg�E'�t�nn�Z=��������{�ڶ�;��� B-C��Ҍ!ϊ�
�bƬ1Rm#��T�ƥ�!�@W.��B���Ľ��/��ým�4ЀP�1� [��&E�#�T@��쪍8����t�~�=�jۜ�\*yv��rp/�N����︈f�Bef�ZeA�NX�a���	�bk�<���E���<d����A����q���}���e��Vy߳�	!ag�	£(&�)�����dA�P"��k��״F��m�W׵�1x+��,����ҽ_��7�HCc�Xz09S9B-���$�� ����L�:�6�j<z0��*������[q��O7���<�w()����Y`�4JJ6�M|�[�a���S�\Zo���m࣭�-��q�u�b�bfm>��������0��upGm�"Թψ�#���L��`l��0D�s�kBh��d$�`��m�L2݂n#�ٽ���h�u<���������Į��	�2���b��u�(�zm��z󜬫��#uʈ���5�׸%6��'�da����ptߚ��/������9)%��DHh�)X��:�ѓ6Hl�U�
�7�V���}�*3:��~M�U�߯6����z�      ?   �   x�U���@C���&���:"��㏵�7IJ�r�P�));�Y��s/�a.�<�x^t~�� x?-�Վ?h�ՏȳE '����4�ZXn`S0v�ʖ$���g�`CfYV6�5�v�ۭٳ���?颎b�L��f����_�)5�      A   %   x�3���2��MM���2�tO�K-J������ k�     