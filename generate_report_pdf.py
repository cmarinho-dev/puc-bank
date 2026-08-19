from reportlab.lib import colors
from reportlab.lib.pagesizes import A4
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, PageBreak, Preformatted

TITLE = "Relatório Acadêmico – Pilha e Fila"


def build_pdf(output_path: str):
    styles = getSampleStyleSheet()
    title_style = ParagraphStyle(
        'TitleStyle',
        parent=styles['Title'],
        fontName='Helvetica-Bold',
        fontSize=22,
        leading=26,
        alignment=1,
        spaceAfter=20,
        textColor=colors.HexColor('#1F2A44')
    )
    heading_style = ParagraphStyle(
        'HeadingStyle',
        parent=styles['Heading2'],
        fontName='Helvetica-Bold',
        fontSize=14,
        leading=18,
        spaceBefore=18,
        spaceAfter=10,
        textColor=colors.HexColor('#243B53')
    )
    body_style = ParagraphStyle(
        'BodyStyle',
        parent=styles['BodyText'],
        fontName='Helvetica',
        fontSize=10.5,
        leading=15,
        spaceAfter=8,
        textColor=colors.HexColor('#1E1E1E')
    )
    code_style = ParagraphStyle(
        'CodeStyle',
        parent=styles['Code'],
        fontName='Courier',
        fontSize=8.5,
        leading=10,
        backColor=colors.HexColor('#F3F4F6'),
        borderPadding=8,
        borderColor=colors.HexColor('#D0D7DE'),
        borderWidth=0.8,
        spaceAfter=12,
    )

    story = []
    story.append(Paragraph(TITLE, title_style))
    story.append(Paragraph("Disciplina: Estruturas de Dados<br/>Tema: Pilha e Fila aplicadas em cenário cotidiano<br/>Valor: 0,5 | Entrega: 17/08", body_style))

    story.append(Paragraph("1. Contextualização", heading_style))
    story.append(Paragraph(
        "Este projeto foi desenvolvido para demonstrar a aplicação prática de duas estruturas fundamentais: pilha e fila. "
        "A pilha representa o estoque de cédulas do ATM, enquanto a fila organiza o atendimento dos clientes em ordem de chegada. "
        "Essas estruturas permitem resolver problemas cotidianos com lógica eficiente e previsível.",
        body_style
    ))

    story.append(Paragraph("2. Pilha – aplicação no ATM", heading_style))
    story.append(Paragraph(
        "A pilha segue o princípio LIFO (Last In, First Out), ou seja, o último elemento incluído é o primeiro a ser removido. "
        "No sistema, cada tipo de cédula é armazenado em um objeto CashStock, que usa Stack<Cash> como estrutura interna.",
        body_style
    ))
    story.append(Preformatted("""public static CashStock fromNewCashes(int quant, CashEnum cashEnum) {\n    var tmpStock = new CashStock(cashEnum);\n    for (int i = 0; i < quant; i++)\n        tmpStock.cashStock.push(new Cash(cashEnum));\n    return tmpStock;\n}\n\npublic String status() {\n    return cashStock.empty() ? \"empty\" : \"occupied\";\n}""", code_style))
    story.append(Paragraph(
        "A operação push() insere novas cédulas; pop() remove a última cédula adicionada; empty() verifica se a pilha está vazia. "
        "Isso é útil para simular a retirada de dinheiro do caixa eletrônico e o controle de notas disponíveis.",
        body_style
    ))

    story.append(Paragraph("3. Status da pilha", heading_style))
    story.append(Paragraph(
        "O status da pilha foi exibido por meio da classe ATM, que percorre todos os estoques e imprime o valor, o estado e a quantidade de notas. "
        "Essa abordagem torna a visualização do hardware do caixa mais clara para análise.",
        body_style
    ))
    story.append(Preformatted("""public void printStatus() {\n    StringBuilder str = new StringBuilder("\\n* ATM STATUS\\n" + "-~-~-~-~-~-~-\\n");\n    StringBuilder values = new StringBuilder();\n    StringBuilder statuses = new StringBuilder();\n    StringBuilder quantities = new StringBuilder();\n\n    for (CashStock stock : stocks.values()) {\n        values.append("%-20s | ".formatted("$%d".formatted(stock.getTypeOfCash().value())));\n        statuses.append("%-20s | ".formatted("status=" + stock.status()));\n        quantities.append("%-20s | ".formatted("quant=" + stock.getCashStock().size()));\n    }\n\n    str.append(values).append("\\n").append(statuses).append("\\n").append(quantities);\n    System.out.println(str);\n}""", code_style))

    story.append(Paragraph("4. Fila – aplicação no atendimento", heading_style))
    story.append(Paragraph(
        "A fila segue o princípio FIFO (First In, First Out). Em um cenário real, o primeiro cliente que chega é o primeiro a ser atendido. "
        "No projeto, essa lógica foi implementada usando Queue<Client> em uma classe dedicada chamada ClientSupportQueue.",
        body_style
    ))
    story.append(Preformatted("""public boolean addClient(Client client) {\n    if (client == null) {\n        System.out.println("Cliente nulo não pode ser adicionado.");\n        return false;\n    }\n\n    if (clients.size() >= getCapacity()) {\n        System.out.println("Fila cheia. Cliente não adicionado: " + client.getName());\n        return false;\n    }\n\n    clients.offer(client);\n    return true;\n}\n\npublic Client attendNextClient() {\n    if (clients.isEmpty()) {\n        System.out.println("Fila vazia.");\n        return null;\n    }\n\n    return clients.poll();\n}""", code_style))

    story.append(Paragraph("5. Status da fila", heading_style))
    story.append(Paragraph(
        "A fila exibiu o tamanho atual e os nomes dos clientes aguardando atendimento. Esse status permite validar o comportamento da estrutura, "
        "incluindo a ordem em que os clientes entram e saem da fila.",
        body_style
    ))
    story.append(Preformatted("""public void printStatus(String title) {\n    System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-");\n    System.out.println("* " + title);\n    System.out.println("tamanho: " + clients.size());\n\n    if (clients.isEmpty()) {\n        System.out.println("fila: vazia");\n    } else {\n        StringBuilder queueStatus = new StringBuilder("fila: ");\n        for (Client client : clients) {\n            queueStatus.append(client.getName()).append(" | ");\n        }\n        System.out.println(queueStatus);\n    }\n}""", code_style))

    story.append(Paragraph("6. Conclusão", heading_style))
    story.append(Paragraph(
        "A implementação mostrou, de maneira prática, como pilha e fila resolvem problemas do cotidiano. A estrutura de pilha foi aplicada ao controle de notas do ATM, enquanto a fila foi usada para organizar o atendimento aos clientes. "
        "Dessa forma, ficou demonstrado o processo de criação, inclusão, exclusão, busca e visualização do status de ambas as estruturas, atendendo ao objetivo acadêmico proposto.",
        body_style
    ))

    story.append(Paragraph("7. Observação final", heading_style))
    story.append(Paragraph(
        "O projeto foi construído em Java, atendendo à proposta de implementação em linguagem orientada a objetos. As estruturas foram aplicadas em cenários reais de uso corporativo e bancário, demonstrando a importância do estudo de pilhas e filas em sistemas computacionais.",
        body_style
    ))

    doc = SimpleDocTemplate(output_path, pagesize=A4, leftMargin=45, rightMargin=45, topMargin=45, bottomMargin=45)
    doc.build(story)


if __name__ == "__main__":
    build_pdf('/workspaces/puc-bank/relatorio-academico.pdf')
    print('PDF gerado: /workspaces/puc-bank/relatorio-academico.pdf')
